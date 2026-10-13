package supermercado.general.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String descripcion;

    @Column(length = 30)
    private String ubicacion;


    // orphanRemoval = true: si un Producto se quita de esta lista, se elimina de BD
    // automáticamente (ya no puede existir sin categoría).
    @OneToMany(mappedBy = "categoria", cascade =CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

    public Categoria() {}

    public Categoria(Long id, String descripcion, String ubicacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }
}