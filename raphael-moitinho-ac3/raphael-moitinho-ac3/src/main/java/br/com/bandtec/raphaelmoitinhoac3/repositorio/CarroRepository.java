package br.com.bandtec.raphaelmoitinhoac3.repositorio;

import br.com.bandtec.raphaelmoitinhoac3.dominio.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Integer> {
    List<Carro> findByVelocidadeMaximaGreaterThan(Integer velocidade);
    List<Carro> findByVelocidadeMaximaIsLessThan(Integer velocidade);
}
