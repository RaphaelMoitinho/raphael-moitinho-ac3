package br.com.bandtec.raphaelmoitinhoac3.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeArquivo {
    public List leArquivo(FileReader arquivo) {

        List<Object> list = new ArrayList<>();
        List<Object> listFilaCategoriaCarro = new ArrayList<>();

        BufferedReader entrada = new BufferedReader(arquivo);

        String carro = "", categoriaCarro = "", leitura = "", tipoRegistro = "", tipoArquivo = "", dataHoraGeracao = "", versaoLayout = "";
        String body = "";
        Integer registroLido = 0, qantidadeRegistro = 0;

        try {
            leitura = entrada.readLine();

            while (leitura != null) {
                tipoRegistro = leitura.substring(0, 2).trim();

                if (tipoRegistro.equals("00")) {
                    tipoArquivo = leitura.substring(2, 8).trim();
                    dataHoraGeracao = leitura.substring(8, 27).trim();
                    versaoLayout = leitura.substring(27, 29).trim();

                } else if (tipoRegistro.equals("02") || tipoRegistro.equals("03")) {
                    list.add(carro = leitura);
                    registroLido++;

                }else if (tipoRegistro.equals("01")){
                    qantidadeRegistro = Integer.parseInt(leitura.substring(2, 7).trim());
                } else {
                    System.out.println("Tipo de leitura inválido");
                }
                body += leitura;
                leitura = entrada.readLine();
            }
            entrada.close();
            if (qantidadeRegistro == registroLido){
                return list;
            }
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }
        return null;
    }
}