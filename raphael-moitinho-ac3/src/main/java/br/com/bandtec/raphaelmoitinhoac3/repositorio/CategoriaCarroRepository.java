package br.com.bandtec.raphaelmoitinhoac3.repositorio;

import br.com.bandtec.raphaelmoitinhoac3.dominio.CategoriaCarro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaCarroRepository extends JpaRepository <CategoriaCarro, Integer> {
}
