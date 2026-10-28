package br.com.senac.montadora_api.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "montadora_id", nullable = false)
    @JsonBackReference
    private Montadora montadora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Montadora getMontadora() {
        return montadora;
    }

    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }

    @OneToMany(mappedBy = "modelo")
    @JsonManagedReference
    private List<Versao> versoes = new ArrayList<>();

    public List<Versao> getVersoes() {
        return versoes;
    }

    public void setVersoes(List<Versao> versoes) {
        this.versoes = versoes;
    }
}
