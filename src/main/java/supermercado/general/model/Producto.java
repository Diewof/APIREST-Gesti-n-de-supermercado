package supermercado.general.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 250)
    private String descripcion;

    @Column(nullable = false)
    private Integer existencia;

    @Column(nullable = false)
    private Double precio;

    @Column(name = "ultimo_ingreso")
    private LocalDate ultimoIngreso;

    @Column(nullable = false)
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    private List<Detalle> detalles;


    @PrePersist
    public void prePersist() {
        ultimoIngreso = LocalDate.now();
    }

    public Producto() {}

    public Producto(Long id, String descripcion, Integer existencia,
                    Double precio, LocalDate ultimoIngreso,
                    boolean disponible, Categoria categoria) {

        this.id = id;
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.precio = precio;
        this.ultimoIngreso = ultimoIngreso;
        this.disponible = disponible;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(LocalDate ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }
}