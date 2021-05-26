package br.com.bandtec.raphaelmoitinhoac3.dominio;

public class CategoriaCarroDto {
    private String protocolo;
    private Integer id;
    private String nome;

    public CategoriaCarroDto(String protocolo, CategoriaCarro categoriaCarro) {
        this.protocolo = protocolo;
        this.id = categoriaCarro.getId();
        this.nome = categoriaCarro.getNome();
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
