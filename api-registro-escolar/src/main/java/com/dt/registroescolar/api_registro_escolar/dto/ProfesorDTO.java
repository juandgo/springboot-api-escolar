package com.dt.registroescolar.api_registro_escolar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorDTO extends PersonaDTO {

    private Long idPersona;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    @NotBlank(message = "La especialidad es obligatoria")
    @Size(max = 100, message = "La especialidad no debe exceder los 100 caracteres")
    private String especialidad;

    @PastOrPresent(message = "La fecha de contratación no puede ser futura")
    private LocalDate fechaContratacion;
}
