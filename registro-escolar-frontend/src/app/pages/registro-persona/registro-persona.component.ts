import { Component } from '@angular/core';
import { PersonaService } from '../../services/persona.service';

export interface PersonaDTO {
  idPersona?: number;
  nombre: string;
  apellido: string;
  email: string;
  telefono: string;
  fechaNacimiento: string;
}

@Component({
  selector: 'app-registro-persona',
  standalone: false,
  templateUrl: './registro-persona.component.html',
  styleUrl: './registro-persona.component.css'
})
export class RegistroPersonaComponent {

  user: PersonaDTO = {
    nombre: '',
    apellido: '',
    email: '',
    telefono: '',
    fechaNacimiento: ''
  };

  constructor(private personaService: PersonaService) {}

  formSubmit(): void {
    this.personaService.registrarPersona(this.user).subscribe({
      next: (res) => {
        alert('Registro exitoso');
        this.user = {
          nombre: '',
          apellido: '',
          email: '',
          telefono: '',
          fechaNacimiento: ''
        };
      },
      error: (err) => {
        console.error(err);
        alert('Error al registrar persona');
      }
    });
  }
}
