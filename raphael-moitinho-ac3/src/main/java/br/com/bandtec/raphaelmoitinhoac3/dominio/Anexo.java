package br.com.bandtec.raphaelmoitinhoac3.dominio;

import javax.persistence.*;

@Entity
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeAnexo;

    // o tipo byte[] é usado para guardar arquivos no banco
    // é recomendável definir o tamanho de campos byte[] (esse tamanho é em bytes)
    // pois alguns bancos usam um tamanho muito pequeno como padrão
    @Column(length = 5_000_000)
    private byte[] conteudoAnexo;

    // GETTERs e SETTERs

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeAnexo() {
        return nomeAnexo;
    }

    public void setNomeAnexo(String nomeAnexo) {
        this.nomeAnexo = nomeAnexo;
    }

    public byte[] getConteudoAnexo() {
        return conteudoAnexo;
    }

    public void setConteudoAnexo(byte[] conteudoAnexo) {
        this.conteudoAnexo = conteudoAnexo;
    }
}
