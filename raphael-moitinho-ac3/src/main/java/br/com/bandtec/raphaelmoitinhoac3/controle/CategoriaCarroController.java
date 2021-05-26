package br.com.bandtec.raphaelmoitinhoac3.controle;

import br.com.bandtec.raphaelmoitinhoac3.dominio.*;
import br.com.bandtec.raphaelmoitinhoac3.repositorio.CategoriaCarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaCarroController {

    private PilhaObj<CategoriaCarro> ultimaOperacao = new PilhaObj<>(20);
    private FilaObj<CategoriaCarroDto> aguardando = new FilaObj<>(20);
    private List<CategoriaCarroDto> lista = new ArrayList<>();


    @Autowired
    private CategoriaCarroRepository repository;

    @GetMapping
    public ResponseEntity getCategorias(){
        List<CategoriaCarro> categorias = repository.findAll();

        if (categorias.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategoria(@PathVariable int id){
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoria(@PathVariable int id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping
    public ResponseEntity postCategoria(@RequestBody CategoriaCarro novaCategoria){
        ultimaOperacao.push(novaCategoria);
        repository.save(novaCategoria);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/desfazer")
    public ResponseEntity deleteDesfazer(){
        if (ultimaOperacao.isEmpty()){
            return ResponseEntity.status(204).body("Não existem operações para serem desfeitas");
        }
        repository.deleteById(ultimaOperacao.pop().getId());
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/requisicao/{id}")
    public ResponseEntity getRequisicao(@PathVariable int id){
        String protocolo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        CategoriaCarro carro = repository.findAll().get(id);
        if (repository.existsById(id)) {
            CategoriaCarroDto proximo = new CategoriaCarroDto(protocolo, carro);
            aguardando.insert(proximo);
            return ResponseEntity.status(202).body("Protocolo: " + protocolo);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/tratar")
    public ResponseEntity getTratar(){
        if (!aguardando.isEmpty()){
            lista.add(aguardando.poll());
            return ResponseEntity.status(202).build();
        }
        return ResponseEntity.status(204).body("Nada a ser tratado");
    }

    @GetMapping("/termino-tratamento/{protocolo}")
    public ResponseEntity getTerminoTratamento(@PathVariable String protocolo){

        for(int i = 0; i < lista.size(); i++){

            if (lista.get(i).getProtocolo().equals(protocolo)){
                CategoriaCarroDto remover = lista.get(i);
                lista.remove(remover);
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.status(404).build();
    }



}
