package br.com.senac.montadora_api.service;

import br.com.senac.montadora_api.dtos.VersaoRequestDto;
import br.com.senac.montadora_api.entidades.Modelo;
import br.com.senac.montadora_api.entidades.Versao;
import br.com.senac.montadora_api.repositorios.VersaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VersaoService {

    @Autowired
    private VersaoRepositorio versaoRepositorio;

    @Autowired
    private ModeloService modeloService;

    public List<Versao> listarTodos() {
        return versaoRepositorio.findAll();
    }

    public Versao criar(VersaoRequestDto versao) {
        Modelo modelo = modeloService.listarPorId(versao.getModeloId());

        Versao versaoPersist = this.versaoRequestDtoParaVersao(versao);
        versaoPersist.setModelo(modelo);

        return versaoRepositorio.save(versaoPersist);
    }

    public Versao atualizar(Long id, VersaoRequestDto versao) {
        if (versaoRepositorio.existsById(id)) {
            Modelo modelo = modeloService.listarPorId(versao.getModeloId());

            Versao versaoPersist = this.versaoRequestDtoParaVersao(versao);
            versaoPersist.setId(id);
            versaoPersist.setModelo(modelo);

            return versaoRepositorio.save(versaoPersist);
        }

        throw new RuntimeException("Versão não encontrada!");
    }

    public void deletar(Long id) {
        if (versaoRepositorio.existsById(id)) {
            versaoRepositorio.deleteById(id);
            return;
        }

        throw new RuntimeException("Versão não encontrada!");
    }

    public Versao listarPorId(Long id) {
        Optional<Versao> versaoRetorno = versaoRepositorio.findById(id);
        if (versaoRetorno.isPresent()) {
            return versaoRetorno.get();
        }

        throw new RuntimeException("Versão não encontrada!");
    }

    private Versao versaoRequestDtoParaVersao(VersaoRequestDto entrada) {
        Versao saida = new Versao();
        saida.setMotor(entrada.getMotor());

        return saida;
    }
}