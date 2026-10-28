package br.com.senac.montadora_api.service;

import br.com.senac.montadora_api.dtos.ModeloRequestDto;
import br.com.senac.montadora_api.entidades.Modelo;
import br.com.senac.montadora_api.entidades.Montadora;
import br.com.senac.montadora_api.repositorios.ModeloRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepositorio modeloRepositorio;

    @Autowired
    private MontadoraService montadoraService;

    public Modelo criar (ModeloRequestDto modelo){
        Montadora montadora = montadoraService.listarPorId(modelo.getMontadoraId());

        Modelo modeloPersit = this.modeloRequestDtoToModelos(modelo);
        modeloPersit.setMontadora(montadora);

        return modeloRepositorio.save(modeloPersit);
    }

    public Modelo atualizar(Long id, ModeloRequestDto modelo){
        if(modeloRepositorio.existsById(id)){
            Montadora montadora = montadoraService.listarPorId(modelo.getMontadoraId());
            Modelo modeloPersit = this.modeloRequestDtoToModelos(modelo);
            modeloPersit.setId(id);

            modeloPersit.setMontadora(montadora);

            return modeloRepositorio.save(modeloPersit);
        }

        throw new RuntimeException("Modelo Não Encontrada!");
    }

    public void deletar(Long id){
        if(modeloRepositorio.existsById(id)){
            modeloRepositorio.deleteById(id);
            return;
        }
        throw new RuntimeException("Modelo Não Encontrada!");
    }

    public List<Modelo> listarTodos() { return modeloRepositorio.findAll();}

    private Modelo modeloRequestDtoToModelos(ModeloRequestDto entrada){
        Modelo saida = new Modelo();
        saida.setNome(entrada.getNome());

        Montadora montadoraRetorno = montadoraService.listarPorId(entrada.getMontadoraId());
        saida.setMontadora(montadoraRetorno);

        return saida;
    }

    public Modelo listarPorId(Long id) {
        Optional<Modelo> modeloRetorno = modeloRepositorio.findById(id);
        if (modeloRetorno.isPresent()) {
            return modeloRetorno.get();
        }

        throw new RuntimeException("Modelo não encontrado!");
    }

}
