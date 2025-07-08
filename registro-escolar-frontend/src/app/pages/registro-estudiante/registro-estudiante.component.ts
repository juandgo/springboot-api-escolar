import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EstudianteDTO } from '../../models/estudiante.dto';
import { EstudianteService } from '../../services/estudiante.service';

@Component({
  selector: 'app-registro-estudiante',
  standalone: false,
  templateUrl: './registro-estudiante.component.html',
})
export class RegistroEstudianteComponent implements OnInit {
  estudiante: EstudianteDTO = {
    id: 0, 
    nombre: '',
    apellido: '',
    email: '',
    telefono: '',
    fechaNacimiento: '',
    numeroMatricula: '',
    grado: '',
  };
  modoEdicion: boolean = false;

  constructor(
    private estudianteService: EstudianteService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.modoEdicion = true;
      this.estudianteService.obtenerEstudiantePorId(+id).subscribe({
        next: (data) => (this.estudiante = data),
        error: (err) => {
          console.error('Error al cargar estudiante', err);
          alert('No se pudo cargar el estudiante.');
        },
      });
    }
  }

  formSubmit(): void {
    const data = { ...this.estudiante };
  
    // Validación opcional
    if (!data.nombre || !data.apellido) {
      alert('Por favor, completa los campos obligatorios');
      return;
    }
  
    if (!this.modoEdicion) {
      // En modo creación, eliminamos idPersona
      delete data.id;
  
      this.estudianteService.registrarEstudiante(data).subscribe({
        next: () => {
          alert('Estudiante registrado con éxito');
          this.router.navigate(['/listar-estudiantes']);
        },
        error: (err) => {
          console.error('Error al registrar estudiante', err);
          alert('Error al registrar estudiante');
        }
      });
    } else {
      if (!data.id) {
        alert('Error: idPersona no está definido para la actualización');
        return;
      }
  
      this.estudianteService.actualizarEstudiante(data).subscribe({
        next: () => {
          alert('Estudiante actualizado con éxito');
          this.router.navigate(['/listar-estudiantes']);
        },
        error: (err) => {
          console.error('Error al actualizar estudiante', err);
          alert('Error al actualizar estudiante');
          console.log('Datos enviados al actualizar:', data);
        }
      });
    }
  }
  
  
}
