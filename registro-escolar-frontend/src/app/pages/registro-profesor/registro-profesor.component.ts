import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfesorDTO } from '../../models/profesor.dto';
import { ProfesorService } from '../../services/profesor.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registro-profesor',
  standalone: false,
  templateUrl: './registro-profesor.component.html'
})
export class RegistroProfesorComponent implements OnInit {
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
    private snack: MatSnackBar
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.modoEdicion = true;
      this.profesorService.obtenerProfesorPorId(+id).subscribe({
        next: (data) => this.profesor = data,
        error: (err) => console.error('Error al cargar profesor', err)
      });
    }
  }

  formSubmit(): void {
    const data = { ...this.profesor };
    if (!this.modoEdicion) {
      delete data.id; // evitar mandar id en el registro
      this.profesorService.registrarProfesor(data).subscribe({
        next: () => {
          // alert('Profesor registrado con éxito');
          Swal.fire(" Profesor registrado ", "Profesor registrado con éxito", "success" );
          this.router.navigate(['/listar-profesores']);
        },
        error: (err) => {
          console.error('Error al registrar profesor', err);
          alert('Error al registrar profesor');
          this.snack.open('Error al registrar profesor', 'Cerrar', {
            duration: 3000,
            verticalPosition: 'top',
            horizontalPosition: 'right',
          });
        }
      });
    } else {
      this.profesorService.actualizarProfesor(data).subscribe({
        next: () => {
          // alert('Profesor actualizado con éxito');
          Swal.fire(" Profesor actualizado ", "Profesor actualizado con éxito", "success" );

          this.router.navigate(['/listar-profesores']);
        },
        error: (err) => {
          console.error('Error al actualizar profesor', err);
          alert('Error al actualizar profesor');
        }
      });
    }
  }
  
}
