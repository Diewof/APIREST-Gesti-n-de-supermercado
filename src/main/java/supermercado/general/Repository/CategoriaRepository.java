package supermercado.general.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import supermercado.general.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Usado en create(): detectar descripción duplicada en cualquier registro
    boolean existsByDescripcionIgnoreCase(String descripcion);

    // Usado en update(): detectar descripción duplicada en un registro DISTINTO al que se edita
    boolean existsByDescripcionIgnoreCaseAndIdNot(String descripcion, Long id);
}
