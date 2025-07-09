import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfesorDTO } from '../../models/profesor.dto';
import { ProfesorService } from '../../services/profesor.service';
import Swal from 'sweetalert2';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registro-profesor',
  standalone: false,
  templateUrl: './registro-profesor.component.html',
})
export class RegistroProfesorComponent implements OnInit {
  profesorForm!: FormGroup;
  profesor: ProfesorDTO = {
    id: 0, // Asegúrate que existe en tu DTO
    nombre: '',
    apellido: '',
    email: '',
    telefono: '',
    fechaNacimiento: '',
    especialidad: '',
    fechaContratacion: '',
  };

  modoEdicion: boolean = false;

  constructor(
    private profesorService: ProfesorService,
    private route: ActivatedRoute,
    private router: Router,
    private snack: MatSnackBar,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.profesorForm = this.fb.group({
      nombre: ['', [Validators.required, Validators.maxLength(50)]],
      apellido: ['', [Validators.required, Validators.maxLength(50)]],
      email: [
        '',
        [Validators.required, Validators.email, Validators.maxLength(100)],
      ],
      telefono: [
        '',
        [Validators.required, Validators.pattern(/^[0-9+\-() ]{7,20}$/)],
      ],
      fechaNacimiento: ['', Validators.required],
      especialidad: ['', [Validators.required, Validators.maxLength(100)]],
      fechaContratacion: ['', Validators.required],
    });

    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.modoEdicion = true;
      this.profesorService.obtenerProfesorPorId(+id).subscribe({
        next: (data) => {
          this.profesor = data;
          this.profesorForm.patchValue(this.profesor); // ✅ importante
        },
        error: (err) => console.error('Error al cargar profesor', err),
      });
    }
  }

  formSubmit(): void {
    if (this.profesorForm.invalid) {
      this.profesorForm.markAllAsTouched(); // activa validaciones visuales
      return;
    }

    const data = this.profesorForm.value; // ✅ aquí usas el formulario

    if (!this.modoEdicion) {
      this.profesorService.registrarProfesor(data).subscribe({
        next: () => {
          Swal.fire(
            'Profesor registrado',
            'Profesor registrado con éxito',
            'success'
          );
          this.router.navigate(['/listar-profesores']);
        },
        error: (err) => {
          console.error('Error al registrar profesor', err);
          this.snack.open('Error al registrar profesor', 'Cerrar', {
            duration: 3000,
            verticalPosition: 'top',
            horizontalPosition: 'right',
          });
        },
      });
    } else {
      const dataConId = { ...data, id: this.profesor.id }; // importante para actualización
      this.profesorService.actualizarProfesor(dataConId).subscribe({
        next: () => {
          Swal.fire(
            'Profesor actualizado',
            'Profesor actualizado con éxito',
            'success'
          );
          this.router.navigate(['/listar-profesores']);
        },
        error: (err) => {
          console.error('Error al actualizar profesor', err);
          alert('Error al actualizar profesor');
        },
      });
    }
  }
}
