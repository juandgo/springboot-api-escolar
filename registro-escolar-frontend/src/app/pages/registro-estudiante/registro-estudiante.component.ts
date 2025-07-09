import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EstudianteDTO } from '../../models/estudiante.dto';
import { EstudianteService } from '../../services/estudiante.service';
import Swal from 'sweetalert2';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registro-estudiante',
  standalone: false,
  templateUrl: './registro-estudiante.component.html',
})
export class RegistroEstudianteComponent implements OnInit {
  estudianteForm!: FormGroup;
  modoEdicion: boolean = false;
  estudianteId?: number;

  constructor(
    private estudianteService: EstudianteService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.estudianteForm = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      numeroMatricula: ['', Validators.required],
      grado: ['', Validators.required],
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.modoEdicion = true;
      this.estudianteId = +id;
      this.estudianteService.obtenerEstudiantePorId(this.estudianteId).subscribe({
        next: (data) => this.estudianteForm.patchValue(data),
        error: (err) => {
          console.error('Error al cargar estudiante', err);
          Swal.fire('Error', 'No se pudo cargar el estudiante', 'error');
        },
      });
    }
  }

  formSubmit(): void {
    if (this.estudianteForm.invalid) return;

    const estudiante: EstudianteDTO = {
      ...this.estudianteForm.value,
      id: this.modoEdicion ? this.estudianteId : undefined,
    };

    if (this.modoEdicion) {
      this.estudianteService.actualizarEstudiante(estudiante).subscribe({
        next: () => {
          Swal.fire('Actualizado', 'Estudiante actualizado con éxito', 'success');
          this.router.navigate(['/listar-estudiantes']);
        },
        error: (err) => {
          console.error('Error al actualizar estudiante', err);
          Swal.fire('Error', 'Error al actualizar estudiante', 'error');
        }
      });
    } else {
      this.estudianteService.registrarEstudiante(estudiante).subscribe({
        next: () => {
          Swal.fire('Registrado', 'Estudiante registrado con éxito', 'success');
          this.router.navigate(['/listar-estudiantes']);
        },
        error: (err) => {
          console.error('Error al registrar estudiante', err);
          Swal.fire('Error', 'Error al registrar estudiante', 'error');
        }
      });
    }
  }
}
