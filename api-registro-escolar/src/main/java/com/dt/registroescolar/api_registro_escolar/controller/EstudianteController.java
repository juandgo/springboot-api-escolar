package com.dt.registroescolar.api_registro_escolar.controller;

import com.dt.registroescolar.api_registro_escolar.entity.Estudiante;
import com.dt.registroescolar.api_registro_escolar.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarEstudiante(@RequestBody Estudiante estudiante) {

        Estudiante estudianteRegistrado = estudianteService.registrarEstudiante(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteRegistrado);
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        return ResponseEntity.ok(estudianteService.listarEstudiantes());
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteService.buscarPorId(id);
        return estudiante.isPresent() ? ResponseEntity.ok(estudiante.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado");
    }

    @GetMapping("/buscar/matricula/{numeroMatricula}")
    public ResponseEntity<?> buscarPorNumeroMatricula(@PathVariable String numeroMatricula) {
        Optional<Estudiante> estudiante = estudianteService.buscarPorNumeroMatricula(numeroMatricula);
        return estudiante.isPresent() ? ResponseEntity.ok(estudiante.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado");
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarEstudiante(
            @PathVariable Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("fechaNacimiento") String fechaNacimiento,
            @RequestParam("email") String email,
            @RequestParam("telefono") String telefono,
            @RequestParam("numeroMatricula") String numeroMatricula,
            @RequestParam("grado") String grado) {

        Estudiante estudianteActualizado = new Estudiante();
        estudianteActualizado.setNombre(nombre);
        estudianteActualizado.setApellido(apellido);
        estudianteActualizado.setFechaNacimiento(java.time.LocalDate.parse(fechaNacimiento));
        estudianteActualizado.setEmail(email);
        estudianteActualizado.setTelefono(telefono);
        estudianteActualizado.setNumeroMatricula(numeroMatricula);
        estudianteActualizado.setGrado(grado);

        try {
            Estudiante actualizado = estudianteService.actualizarEstudiante(id, estudianteActualizado);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEstudiante(@PathVariable Long id) {
        try {
            estudianteService.eliminarEstudiante(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar: " + e.getMessage());
        }
    }
}
