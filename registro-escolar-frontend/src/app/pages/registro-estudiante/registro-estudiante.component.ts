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
    nombre: '',
    apellido: '',
    email: '',
    telefono: '',
    fechaNacimiento: '',
    numeroMatricula: '',
    grado: '',
  };

  esModoEdicion = false;

  constructor(
    private estudianteService: EstudianteService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.esModoEdicion = true;
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
    if (this.esModoEdicion && this.estudiante.id) {
      this.estudianteService.actualizarEstudiante(this.estudiante).subscribe({
        next: () => {
          alert('Estudiante actualizado correctamente');
          this.router.navigate(['/estudiantes']);
        },
        error: (err) => {
          console.error('Error al actualizar estudiante', err);
          alert('Error al actualizar');
        },
      });
    } else {
      this.estudianteService.registrarEstudiante(this.estudiante).subscribe({
        next: () => {
          alert('Estudiante registrado con éxito');
          this.router.navigate(['/estudiantes']);
        },
        error: (err) => {
          console.error('Error al registrar estudiante', err);
          alert('Error al registrar');
        },
      });
    }
  }
}
