package br.com.senac.montadora_api.service;


import br.com.senac.montadora_api.dtos.MontadoraRequestDto;
import br.com.senac.montadora_api.entidades.Montadora;
import br.com.senac.montadora_api.repositorios.MontadoraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MontadoraService {

    @Autowired
    private MontadoraRepositorio montadoraRepositorio;

    public List<Montadora> listarTodos(){
        return montadoraRepositorio.findAll();
    }

    public Montadora criar(MontadoraRequestDto montadora){
        Montadora montadoraPersit = this.montadoraRequestDtoParaMontadora(montadora);

        return montadoraRepositorio.save(montadoraPersit);
    }

    public Montadora atualizar(Long id, MontadoraRequestDto montadora){
        if(montadoraRepositorio.existsById(id)){
            Montadora montadoraPersit = this.montadoraRequestDtoParaMontadora(montadora);
            montadoraPersit.setId(id);

            return montadoraRepositorio.save(montadoraPersit);
        }
        throw new RuntimeException("Montadora não Encontrada!");
    }

    public void deletar(Long id){
        if(montadoraRepositorio.existsById(id)){
                montadoraRepositorio.deleteById(id);
                return;
        }
        throw new RuntimeException("Montadora não Encontrada!");
    }

    public Montadora listarPorId(Long id){
        Optional<Montadora> montadoraRetorno =montadoraRepositorio.findById(id);
        if(montadoraRetorno.isPresent()){
            return montadoraRetorno.get();
        }

        throw new RuntimeException("Montadora não Encontrada!");
    }

    private Montadora montadoraRequestDtoParaMontadora(MontadoraRequestDto entrada){
        Montadora saida = new Montadora();
        saida.setNome(entrada.getNome());

        return saida;
    }
}
