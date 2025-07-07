package com.dt.registroescolar.api_registro_escolar.controller;

import com.dt.registroescolar.api_registro_escolar.dto.PersonaDTO;
import com.dt.registroescolar.api_registro_escolar.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin("*")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/registrar")
    public ResponseEntity<PersonaDTO> registrarPersona(@Valid @RequestBody PersonaDTO personaDTO) {
        PersonaDTO personaRegistrada = personaService.registrarPersona(personaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaRegistrada);
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> listarPersonas() {
        return ResponseEntity.ok(personaService.listarPersonas());
    }

    @GetMapping("/buscar/id/{idPersona}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idPersona) {
        Optional<PersonaDTO> personaDTO = personaService.buscarPorId(idPersona);
        return personaDTO.isPresent() ? ResponseEntity.ok(personaDTO.get())
                :ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
    }

//    @GetMapping("/buscar/email/{email}")
//    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
//        Optional<PersonaDTO> persona = personaService.buscarPorEmail(email);
//        return persona.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada"));
//    }

    @GetMapping("/buscar/apellido/{apellido}")
    public ResponseEntity<List<PersonaDTO>> buscarPorApellido(@PathVariable String apellido) {
        List<PersonaDTO> personas = personaService.buscarPorApellido(apellido);
        return ResponseEntity.ok(personas);
    }

    @PutMapping("/actualizar/{idPersona}")
    public ResponseEntity<?> actualizarPersona(
            @PathVariable Long idPersona,
            @Valid @RequestBody PersonaDTO personaDTO) {

        try {
            PersonaDTO personaActualizada = personaService.actualizarPersona(idPersona, personaDTO);
            return ResponseEntity.ok(personaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{idPersona}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Long idPersona) {
        try {
            personaService.eliminarPersona(idPersona);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }
}
