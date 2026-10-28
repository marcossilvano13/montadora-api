package br.com.senac.montadora_api.repositorios;

import br.com.senac.montadora_api.entidades.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepositorio extends JpaRepository<Modelo, Long> {
}
