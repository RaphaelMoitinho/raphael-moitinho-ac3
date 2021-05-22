package br.com.bandtec.raphaelmoitinhoac3.controle;

import br.com.bandtec.raphaelmoitinhoac3.dominio.Carro;
import br.com.bandtec.raphaelmoitinhoac3.dominio.CategoriaCarro;
import br.com.bandtec.raphaelmoitinhoac3.repositorio.CategoriaCarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaCarroController {

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
        repository.save(novaCategoria);
        return ResponseEntity.status(201).build();
    }
}
