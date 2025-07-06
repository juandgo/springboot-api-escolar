package com.dt.registroescolar.api_registro_escolar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteDTO extends PersonaDTO {

    @NotBlank(message = "El número de matrícula es obligatorio")
    @Size(max = 50, message = "El número de matrícula no debe exceder los 50 caracteres")
    private String numeroMatricula;

    @NotBlank(message = "El grado es obligatorio")
    @Size(max = 50, message = "El grado no debe exceder los 50 caracteres")
    private String grado;
}
