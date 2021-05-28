package br.com.bandtec.raphaelmoitinhoac3.controle;

import br.com.bandtec.raphaelmoitinhoac3.dominio.Carro;
import br.com.bandtec.raphaelmoitinhoac3.dominio.CategoriaCarro;
import br.com.bandtec.raphaelmoitinhoac3.repositorio.CarroRepository;
import br.com.bandtec.raphaelmoitinhoac3.repositorio.CategoriaCarroRepository;
import br.com.bandtec.raphaelmoitinhoac3.util.FilaObj;
import br.com.bandtec.raphaelmoitinhoac3.util.LeArquivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LeArquivoController {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private CategoriaCarroRepository categoriaCarroRepository;

    FilaObj<Carro> filaCarro = new FilaObj<>(999);
    FilaObj<CategoriaCarro> filaCategoriaCarro = new FilaObj<>(999);

    LeArquivo leArquivo = new LeArquivo();

    Integer idCarro, idCategoria, idCarroCategoria, velocidadeMaximaCarro;
    String nomeCarro = "", marcaCarro = "";
    String nomeCategoria = "";

    List arquivoLido = new ArrayList();

    @PostMapping("/carros/importar-arquivos")
    public ResponseEntity importart(@RequestParam MultipartFile arquivo) throws IOException { ;

        if (!arquivo.isEmpty()) {

            File file = new File(arquivo.getOriginalFilename());
            arquivoLido = leArquivo.leArquivo(new FileReader(file));

            for (int i = 0; i < arquivoLido.size(); i++){

                String linha = arquivoLido.get(i).toString();

                if (linha.substring(0, 2).equals("02")){
                    idCarro = Integer.parseInt(linha.substring(2, 6).trim());
                    nomeCarro = linha.substring(6, 26).trim();
                    marcaCarro = linha.substring(26, 46).trim();
                    velocidadeMaximaCarro = Integer.parseInt(linha.substring(46, 49).trim());
                    idCarroCategoria = Integer.parseInt(linha.substring(49, 51).trim());
                    CategoriaCarro categoriaCarro = categoriaCarroRepository.getOne(idCarroCategoria);
                    Carro carro = new Carro(idCarro, nomeCarro, marcaCarro, velocidadeMaximaCarro, categoriaCarro);
                    filaCarro.insert(carro);
                }
                if (linha.substring(0, 2).equals("03")){
                    idCategoria = Integer.parseInt(linha.substring(2, 4).trim());
                    nomeCategoria = linha.substring(4, 24).trim();
                    CategoriaCarro categoriaCarro = new CategoriaCarro(idCategoria, nomeCategoria);
                    filaCategoriaCarro.insert(categoriaCarro);
                }

            }

            while (!filaCategoriaCarro.isEmpty()){
                categoriaCarroRepository.save(filaCategoriaCarro.poll());
            }
            while (!filaCarro.isEmpty()){
                carroRepository.save(filaCarro.poll());
            }

            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(400).build();
    }
}
