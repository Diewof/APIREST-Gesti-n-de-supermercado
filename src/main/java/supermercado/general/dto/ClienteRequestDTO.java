package supermercado.general.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(

    @NotNull(message = "La identificación es obligatoria")
    @Min(value = 1, message = "La identificación debe ser un número positivo")
    Long identificacion,

    @NotBlank(message = "El tipo de identificación es obligatorio")
    @Pattern(
        regexp = "CC|CE|NIT|PASAPORTE",
        message = "Tipo de identificación inválido. Use: CC, CE, NIT o PASAPORTE"
    )
    String tipoIdentificacion,

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 3, max = 60, message = "El nombre completo debe tener entre 3 y 60 caracteres")
    String nombreCompleto,

    @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
    String direccion,

    @Size(max = 255, message = "El teléfono no puede superar los 255 caracteres")
    String telefono,

    @Email(message = "El correo electrónico no tiene un formato válido")
    @Size(max = 60, message = "El correo no puede superar los 60 caracteres")
    String correoElectronico,

    @Min(value = 0, message = "La capacidad de crédito no puede ser negativa")
    Integer capacidadCredito,

    LocalDate fechaIngreso

) {}
