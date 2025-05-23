package com.capibyte.acervo.infraestrutura.persistencia.core.acervo.obra;

import com.capibyte.acervo.infraestrutura.persistencia.core.acervo.autor.AutorJPA;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Obra")
public class ObraJPA {

    @Id
    private String doi;
    private String titulo;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "autor_obra",
            joinColumns = @JoinColumn(name = "doi"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<AutorJPA> autoresObra;
    @ElementCollection
    @CollectionTable(
            name = "obras_temas",
            joinColumns = @JoinColumn(name = "doi")
    )
    @Column(name = "palavrasChave")
    private List<String> palavrasChave;
    private String resumo;
    private LocalDate dataPublicacao;
    private String citacaoAbnt;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "arquivo_pdf", nullable = true)
    private byte[] arquivoPDF;

    @Column(name = "validado")
    private boolean validado;

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<AutorJPA> getAutoresObra() {
        return autoresObra;
    }

    public void setAutoresObra(List<AutorJPA> autoresObra) {
        this.autoresObra = autoresObra;
    }

    public List<String> getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(List<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getCitacaoAbnt() {
        return citacaoAbnt;
    }

    public void setCitacaoAbnt(String citacaoAbnt) {
        this.citacaoAbnt = citacaoAbnt;
    }

    public byte[] getArquivoPDF() {
        return arquivoPDF;
    }

    public void setArquivoPDF(byte[] arquivoPDF) {
        this.arquivoPDF = arquivoPDF;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }
}
