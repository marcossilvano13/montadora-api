package br.com.senac.montadora_api.dtos;

public class VersaoRequestDto {
    private String motor;
    private Long modelo_Id;

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public Long getModelo_Id() {
        return modelo_Id;
    }

    public void setModelo_Id(Long modelo_Id) {
        this.modelo_Id = modelo_Id;
    }
}
