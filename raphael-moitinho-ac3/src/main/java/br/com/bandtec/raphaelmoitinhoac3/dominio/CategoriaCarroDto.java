package br.com.bandtec.raphaelmoitinhoac3.dominio;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class CategoriaCarroDto {

    @Id
    private String protocolo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

    private Integer id;

    private String nome;

    public CategoriaCarroDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaCarroDto() {
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
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
