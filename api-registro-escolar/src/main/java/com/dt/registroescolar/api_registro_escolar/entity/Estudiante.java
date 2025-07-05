package com.dt.registroescolar.api_registro_escolar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_persona")
public class Estudiante extends Persona {

    @Column(name = "numero_matricula", nullable = false, unique = true, length = 50)
    private String numeroMatricula;

    @Column(nullable = false, length = 50)
    private String grado;
}

