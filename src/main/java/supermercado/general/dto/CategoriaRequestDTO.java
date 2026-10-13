package supermercado.general.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(

    @NotBlank(message = "La descripción de la categoría es obligatoria")
    @Size(min = 3, max = 80, message = "La descripción debe tener entre 3 y 80 caracteres")
    String descripcion,

    @Size(max = 30, message = "La ubicación no puede superar los 30 caracteres")
    String ubicacion

) {}
