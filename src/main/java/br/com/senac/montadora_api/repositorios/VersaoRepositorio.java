package br.com.senac.montadora_api.repositorios;

import br.com.senac.montadora_api.entidades.Versao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersaoRepositorio extends JpaRepository<Versao, Long> {
}
