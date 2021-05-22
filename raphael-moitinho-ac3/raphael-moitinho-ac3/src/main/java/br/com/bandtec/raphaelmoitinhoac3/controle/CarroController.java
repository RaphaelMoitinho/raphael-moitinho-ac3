package br.com.bandtec.raphaelmoitinhoac3.controle;

import br.com.bandtec.raphaelmoitinhoac3.dominio.Carro;
import br.com.bandtec.raphaelmoitinhoac3.repositorio.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {
//http://localhost:8080/h2-console/login.jsp?jsessionid=24a41465a26dc2fb95aad8db73146d56

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
        repository.save(novoCarro);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/turbinar/{id}")
    public ResponseEntity turbinarCarro(@Valid @PathVariable int id){
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

}
