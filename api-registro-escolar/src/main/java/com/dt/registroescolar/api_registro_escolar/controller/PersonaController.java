package com.dt.registroescolar.api_registro_escolar.controller;

import com.dt.registroescolar.api_registro_escolar.entity.Persona;
import com.dt.registroescolar.api_registro_escolar.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/registrar")
    public ResponseEntity<Persona> registrarPersona(@RequestBody Persona persona) {
        Persona personaRegistrada = personaService.registrarPersona(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaRegistrada);
    }

    @GetMapping
    public ResponseEntity<List<Persona>> listarPersonas() {
        return ResponseEntity.ok(personaService.listarPersonas());
    }

    @GetMapping("/buscar/id/{idPersona}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idPersona) {
        Optional<Persona> persona = personaService.buscarPorId(idPersona);
        return persona.isPresent() ? ResponseEntity.ok(persona.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
    }

    @PutMapping("/actualizar/{idPersona}")
    public ResponseEntity<?> actualizarPersona(
            @PathVariable Long idPersona,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("fechaNacimiento") LocalDate fechaNacimiento,
            @RequestParam("email") String email,
            @RequestParam("telefono") String telefono) {

        try {
            Persona persona = new Persona();
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setFechaNacimiento(fechaNacimiento);
            persona.setEmail(email);
            persona.setTelefono(telefono);

            Persona personaActualizada = personaService.actualizarPersona(idPersona, persona);
            return ResponseEntity.ok(personaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
