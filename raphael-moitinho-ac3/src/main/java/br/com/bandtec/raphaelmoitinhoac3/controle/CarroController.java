package br.com.bandtec.raphaelmoitinhoac3.controle;

import br.com.bandtec.raphaelmoitinhoac3.dominio.Carro;
import br.com.bandtec.raphaelmoitinhoac3.dominio.FilaObj;
import br.com.bandtec.raphaelmoitinhoac3.dominio.PilhaObj;
import br.com.bandtec.raphaelmoitinhoac3.repositorio.CarroRepository;
import br.com.bandtec.raphaelmoitinhoac3.dominio.CarroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private PilhaObj<Carro> ultimaOperacao = new PilhaObj<>(20);
    private FilaObj<CarroDto> aguardando = new FilaObj<>(20);
    private List<CarroDto> lista = new ArrayList<>();

    @Autowired
    private CarroRepository repository;

    @GetMapping
    public ResponseEntity getCarros(){
        List<Carro> carros = repository.findAll();

        if (carros.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCarro(@PathVariable int id){
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCarro(@PathVariable int id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping
    public ResponseEntity postCarro(@Valid @RequestBody Carro novoCarro){
        ultimaOperacao.push(novoCarro);
        repository.save(novoCarro);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/turbinar/{id}")
    public ResponseEntity PutTurbinarCarro(@Valid @PathVariable int id){
        if (repository.existsById(id)){
            Carro carro = repository.findAll().get(id-1);
            carro.setVelocidadeMaxima(carro.getVelocidadeMaxima()+100);
            repository.save(carro);
            return ResponseEntity.status(201).body(carro);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping ("/mais-rapidos")
    public ResponseEntity getCarroRapido(){
        List<Carro> maisRapido = repository.findByVelocidadeMaximaGreaterThan(200);
        if (maisRapido.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(maisRapido);
    }

    @GetMapping ("/mais-lentos")
    public ResponseEntity getCarroLento(){
        List<Carro> maisLento = repository.findByVelocidadeMaximaIsLessThan(100);
        if (maisLento.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(maisLento);
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
        Carro carro = repository.findAll().get(id);
       if (repository.existsById(id)) {
           CarroDto proximo = new CarroDto(protocolo, carro);
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
                CarroDto remover = lista.get(i);
                lista.remove(remover);
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.status(404).build();
    }
}
