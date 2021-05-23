package br.com.bandtec.raphaelmoitinhoac3.servico;

import java.time.LocalDateTime;

public class CategoriaCarroDto {

    private LocalDateTime protocolo;
    private Integer id;
    private String nome;

    public CategoriaCarroDto(Integer id, String nome) {
        this.protocolo = LocalDateTime.now();
        this.id = id;
        this.nome = nome;
    }

    public LocalDateTime getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(LocalDateTime protocolo) {
        this.protocolo = protocolo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
