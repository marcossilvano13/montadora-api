package br.com.senac.montadora_api.dtos;

public class ModeloRequestDto {
    private String nome;
    private Long montadoraId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getMontadoraId() {
        return montadoraId;
    }

    public void setMontadoraid(Long montadora_id) {
        this.montadoraId = montadora_id;
    }
}
