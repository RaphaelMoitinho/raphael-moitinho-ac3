package br.com.bandtec.raphaelmoitinhoac3.controle;

import br.com.bandtec.raphaelmoitinhoac3.dominio.Anexo;
import br.com.bandtec.raphaelmoitinhoac3.repositorio.AnexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("carros/arquivos")
public class AnexoController {
    @Autowired
    private AnexoRepository repository;

    @PostMapping
    public ResponseEntity criarArquivo(@RequestParam MultipartFile arquivo) throws IOException {

        if (arquivo.isEmpty()) {
            return ResponseEntity.status(400).body("Arquivo não enviado!");
        }

        System.out.println("Recebendo um arquivo de nome: " + arquivo.getOriginalFilename());
        System.out.println("Recebendo um arquivo do tipo: " + arquivo.getContentType());

        byte[] conteudo = arquivo.getBytes();

        Path path = Paths.get(arquivo.getOriginalFilename());
        Files.write(path, conteudo);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/anexo")
    public ResponseEntity criarAnexo(@RequestParam MultipartFile arquivo) throws IOException {

        if (arquivo.isEmpty()) {
            return ResponseEntity.status(400).body("Arquivo não enviado!");
        }

        Anexo novoAnexo = new Anexo();
        novoAnexo.setNomeAnexo(arquivo.getOriginalFilename());
        novoAnexo.setConteudoAnexo(arquivo.getBytes());

        repository.save(novoAnexo);

        return ResponseEntity.status(201).build();
    }
}
