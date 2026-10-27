package br.com.senac.montadora_api.dtos;

public class ModeloRequestDto {
    private String nome;
    private Long montadora_id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getMontadora_id() {
        return montadora_id;
    }

    public void setMontadora_id(Long montadora_id) {
        this.montadora_id = montadora_id;
    }
}
