import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfesorDTO } from '../../models/profesor.dto';
import { ProfesorService } from '../../services/profesor.service';

@Component({
  selector: 'app-registro-profesor',
  standalone: false,
  templateUrl: './registro-profesor.component.html'
})
export class RegistroProfesorComponent implements OnInit {
  profesor: ProfesorDTO = {
    idPersona: 0, // Asegúrate que existe en tu DTO
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
    private router: Router
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
      delete data.idPersona; // evitar mandar id en el registro
      this.profesorService.registrarProfesor(data).subscribe({
        next: () => {
          alert('Profesor registrado con éxito');
          this.router.navigate(['/listar-profesores']);
        },
        error: (err) => {
          console.error('Error al registrar profesor', err);
          alert('Error al registrar profesor');
        }
      });
    } else {
      this.profesorService.actualizarProfesor(data).subscribe({
        next: () => {
          alert('Profesor actualizado con éxito');
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
