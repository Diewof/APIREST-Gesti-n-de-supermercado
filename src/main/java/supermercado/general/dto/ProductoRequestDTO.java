package supermercado.general.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductoRequestDTO(

    @NotBlank(message = "La descripción del producto es obligatoria")
    @Size(min = 2, max = 250, message = "La descripción debe tener entre 2 y 250 caracteres")
    String descripcion,

    @NotNull(message = "La existencia es obligatoria")
    @Min(value = 0, message = "La existencia no puede ser negativa")
    Integer existencia,

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a cero")
    Double precio,

    LocalDate ultimoIngreso,

    boolean disponible,

    @NotNull(message = "El ID de categoría es obligatorio")
    Long categoriaId

) {}
