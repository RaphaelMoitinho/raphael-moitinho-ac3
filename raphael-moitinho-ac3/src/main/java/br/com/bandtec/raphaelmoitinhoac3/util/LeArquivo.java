package br.com.bandtec.raphaelmoitinhoac3.util;

import br.com.bandtec.raphaelmoitinhoac3.dominio.Carro;
import br.com.bandtec.raphaelmoitinhoac3.dominio.CarroDto;
import br.com.bandtec.raphaelmoitinhoac3.dominio.CategoriaCarro;
import br.com.bandtec.raphaelmoitinhoac3.repositorio.CarroRepository;
import br.com.bandtec.raphaelmoitinhoac3.repositorio.CategoriaCarroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LeArquivo {

    @Autowired
    private CategoriaCarroRepository repositoryCategoria;
    public FilaObj<Object> corpoArquivo = new FilaObj<>(999);

    public Object leArquivo(FileReader arquivo) {

        FilaObj<Carro> filaCarro = new FilaObj<>(999);
        FilaObj<CategoriaCarro> filaCategoria = new FilaObj<>(999);

        BufferedReader entrada = new BufferedReader(arquivo);

        Integer idCarro, idCategoria, idCarroCategoria, velocidadeMaximaCarro;
        String leitura = "", tipoRegistro = "", tipoArquivo = "", dataHoraGeracao = "", versaoLayout = "";
        String nomeCarro = "", marcaCarro = "";
        String nomeCategoria = "";
        String body = "";
        Carro carro = null;
        CategoriaCarro categoriaCarro = null;
        int registroLido = 0;

        try {
            leitura = entrada.readLine();

            while (leitura != null) {
                tipoRegistro = leitura.substring(0, 2).trim();

                if (tipoRegistro.equals("00")) {
                    tipoArquivo = leitura.substring(2, 8).trim();
                    dataHoraGeracao = leitura.substring(8, 27).trim();
                    versaoLayout = leitura.substring(27, 29).trim();

                } else if (tipoRegistro.equals("02")) {
                    idCarro = Integer.parseInt(leitura.substring(2, 6).trim());
                    nomeCarro = leitura.substring(6, 26).trim();
                    marcaCarro = leitura.substring(26, 46).trim();
                    velocidadeMaximaCarro = Integer.parseInt(leitura.substring(46, 49).trim());
                    idCarroCategoria = Integer.parseInt(leitura.substring(49, 51).trim());
                    categoriaCarro = this.repositoryCategoria.getOne(idCarroCategoria);
                    carro = new Carro(idCarro, nomeCarro, marcaCarro, velocidadeMaximaCarro, categoriaCarro);
                    filaCarro.insert(carro);
                    registroLido++;

                } else if (tipoRegistro.equals("03")) {
                    idCategoria = Integer.parseInt(leitura.substring(2, 4).trim());
                    nomeCategoria = leitura.substring(4, 24).trim();
                    categoriaCarro = new CategoriaCarro(idCategoria, nomeCategoria);
                    filaCategoria.insert(categoriaCarro);
                    registroLido++;


                }else if (tipoRegistro.equals("01")){
                    Integer qantidadeRegistro = Integer.parseInt(leitura.substring(2, 7).trim());
                    corpoArquivo.insert(categoriaCarro);
                    corpoArquivo.insert(carro);

                } else {
                    System.out.println("Tipo de leitura inválido");
                }
                body += leitura;
                leitura = entrada.readLine();
            }
            entrada.close();


            return null;
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }
        return corpoArquivo;
    }
}