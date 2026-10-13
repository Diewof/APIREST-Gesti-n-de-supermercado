package supermercado.general.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import supermercado.general.model.Producto;

// Repositorio JPA. Spring Data genera la implementación automáticamente en tiempo de arranque.
// JpaRepository<Producto, Long> provee save, findById, findAll, deleteById, existsById, etc.
public interface ProductRepository extends JpaRepository<Producto, Long> {

    // Búsqueda por descripción (LIKE insensible a mayúsculas) y rango de precio.
    // El patrón ":param is null or condicion" hace cada filtro opcional:
    // si el parámetro es null, esa condición se ignora completamente.
    @Query("""
        select p from Producto p
        where (:descripcion is null or lower(p.descripcion) like lower(concat('%', :descripcion, '%')))
        and (:minPrecio is null or p.precio >= :minPrecio)
        and (:maxPrecio is null or p.precio <= :maxPrecio)
    """)
    List<Producto> search(
        @Param("descripcion") String descripcion,
        @Param("minPrecio") Double minPrecio,
        @Param("maxPrecio") Double maxPrecio
    );

    // Búsqueda por descripción y rango de existencia (stock). Misma lógica de filtros opcionales.
    @Query("""
        select p from Producto p
        where (:descripcion is null or lower(p.descripcion) like lower(concat('%', :descripcion, '%')))
        and (:minExistencia is null or p.existencia >= :minExistencia)
        and (:maxExistencia is null or p.existencia <= :maxExistencia)
    """)
    List<Producto> searchByDescriptionAndStock(
        @Param("descripcion") String descripcion,
        @Param("minExistencia") Double minExistencia,
        @Param("maxExistencia") Double maxExistencia
    );

    Optional<Producto> findByCategoriaIgnoreCase(String categoria);

    Optional<Producto> findByDisponibleAndCategoria(boolean disponible, String categoria);

    // Se usa en create() para verificar duplicados antes del INSERT
    Optional<Producto> findByDescripcionIgnoreCase(String descripcion);

    @Query("select p from Producto p where lower(p.descripcion) = lower(:descripcion) and p.precio = :precio")
    Optional<Producto> buscarPorDescripcionYPrecio(
        @Param("descripcion") String descripcion,
        @Param("precio") Double precio
    );

    @Query("""
        select p from Producto p
        where (:minExistencia is null or p.existencia >= :minExistencia)
        and (:maxExistencia is null or p.existencia <= :maxExistencia)
    """)
    List<Producto> buscarPorExistencia(
        @Param("minExistencia") Integer minExistencia,
        @Param("maxExistencia") Integer maxExistencia
    );

}
