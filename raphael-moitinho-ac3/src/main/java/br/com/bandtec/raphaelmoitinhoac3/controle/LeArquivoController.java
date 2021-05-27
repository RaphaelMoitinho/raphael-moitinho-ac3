package br.com.bandtec.raphaelmoitinhoac3.controle;

import br.com.bandtec.raphaelmoitinhoac3.util.FilaObj;
import br.com.bandtec.raphaelmoitinhoac3.util.LeArquivo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class LeArquivoController {
    LeArquivo leArquivo = new LeArquivo();

    @PostMapping("/carros/importar-arquivos")
    public ResponseEntity<String> importart(@RequestParam MultipartFile arquivo) throws IOException { ;

        if (!arquivo.isEmpty()) {
            File file = new File(arquivo.getOriginalFilename());
            leArquivo.leArquivo(new FileReader(file));
            return ResponseEntity.status(200).build();

        }
        return ResponseEntity.status(400).build();
    }
}
//while (filaCategoria.isEmpty()){
//        this.repositoryCategoria.save(categoriaCarro);
//        }
//        while (filaCarro.isEmpty()){
//        this.repositoryCarro.save(carro);
//        }
