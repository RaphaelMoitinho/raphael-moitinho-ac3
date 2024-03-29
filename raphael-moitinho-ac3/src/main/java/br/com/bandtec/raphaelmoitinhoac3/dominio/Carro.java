package br.com.bandtec.raphaelmoitinhoac3.dominio;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Max(9999)
    private Integer id;

    @NotBlank
    @Size(max = 20)
    private String nome;

    @NotBlank
    @Size(max = 20)
    private String marca;

    @NotNull
    @PositiveOrZero
    @Max(999)
    private Integer velocidadeMaxima;

    @ManyToOne
    private CategoriaCarro categoria;

    public Carro() {
    }
    public Carro(Integer id, String nome, String marca, Integer velocidadeMaxima, CategoriaCarro categoria) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.velocidadeMaxima = velocidadeMaxima;
        this.categoria = categoria;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public void setVelocidadeMaxima(Integer velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public CategoriaCarro getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCarro categoria) {
        this.categoria = categoria;
    }
}
