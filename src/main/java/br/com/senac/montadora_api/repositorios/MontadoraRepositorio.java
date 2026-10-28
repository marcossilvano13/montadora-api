package br.com.senac.montadora_api.repositorios;

import br.com.senac.montadora_api.entidades.Montadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MontadoraRepositorio extends JpaRepository<Montadora, Long> {
}
