package com.dt.registroescolar.api_registro_escolar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_persona")
public class Profesor extends Persona {

    @Column(nullable = false, length = 100)
    private String especialidad;

    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;
}
