package br.com.bandtec.raphaelmoitinhoac3.dominio;

public class CarroDto {

    private String protocolo;
    private Integer id;
    private String nome;
    private String marca;
    private Integer velocidadeMaxima;
    private Integer idCategoria;
    private String nomeCategoria;

    public CarroDto(String protocolo, Carro carro) {
        this.protocolo = protocolo;
        this.id = carro.getId();
        this.nome = carro.getNome();
        this.marca = carro.getMarca();
        this.velocidadeMaxima = carro.getVelocidadeMaxima();
        this.idCategoria = carro.getCategoria().getId();
        this.nomeCategoria = carro.getCategoria().getNome();
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

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
