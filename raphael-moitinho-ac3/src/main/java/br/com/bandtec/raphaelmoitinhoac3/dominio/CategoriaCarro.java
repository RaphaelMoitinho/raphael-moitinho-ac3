package br.com.bandtec.raphaelmoitinhoac3.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class CategoriaCarro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Max(99)
    private Integer id;

    @NotBlank
    @Size(max = 20)
    private String nome;

    public CategoriaCarro() {
    }

    public CategoriaCarro(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
