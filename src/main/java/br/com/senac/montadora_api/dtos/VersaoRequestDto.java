package br.com.senac.montadora_api.dtos;

public class VersaoRequestDto {
    private String motor;
    private Long modeloId;

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public Long getModeloId() {
        return modeloId;
    }

    public void setModeloId(Long modelo_Id) {
        this.modeloId = modelo_Id;
    }
}
