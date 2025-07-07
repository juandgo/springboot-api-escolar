// src/app/pages/registro-profesor/registro-profesor.component.ts
import { Component, OnInit } from '@angular/core';
import { ProfesorDTO } from '../../models/profesor.dto';
import { ProfesorService } from '../../services/profesor.service';

@Component({
  selector: 'app-registro-profesor',
  standalone: false,
  templateUrl: './registro-profesor.component.html'
})
export class RegistroProfesorComponent implements OnInit {
  profesor: ProfesorDTO = {
    nombre: '',
    apellido: '',
    email: '',
    telefono: '',
    fechaNacimiento: '',
    especialidad: ''
  };

  profesores: ProfesorDTO[] = [];

  constructor(private profesorService: ProfesorService) {}

  ngOnInit(): void {
    this.profesorService.listarProfesores().subscribe({
      next: (data) => this.profesores = data,
      error: (err) => console.error('Error al obtener profesores:', err)
    });
  }

  formSubmit(): void {
    this.profesorService.registrarProfesor(this.profesor).subscribe({
      next: () => {
        alert('Profesor registrado con éxito');
        this.profesor = {
          nombre: '',
          apellido: '',
          email: '',
          telefono: '',
          fechaNacimiento: '',
          especialidad: ''
        };
      },
      error: (err) => {
        console.error(err);
        alert('Error al registrar profesor');
      }
    });
  }
}
