import { Component, OnInit } from '@angular/core';
import { ProfesorDTO } from '../../models/profesor.dto'; // Adjust the path as needed
import { ProfesorService } from '../../services/profesor.service'; // Adjust the path as needed
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-profesores',
  standalone: false,
  templateUrl: './listar-profesores.component.html',
  styleUrl: './listar-profesores.component.css'
})
export class ListarProfesoresComponent implements OnInit {
  profesores: ProfesorDTO[] = [];
  columnas: string[] = ['nombre', 'apellido', 'email', 'telefono', 'especialidad', 'acciones'];

  constructor(private profesorService: ProfesorService, private router: Router) {}

  ngOnInit(): void {
    this.obtenerProfesores();
  }

  obtenerProfesores(): void {
    this.profesorService.listarProfesores().subscribe(
      {
      next: (data) => {
        this.profesores = data;
        console.log('Profesores recibidos:', this.profesores);
      },
      error: (err) => console.error('Error al obtener profesores:', err)
    });
  }

  editarProfesor(profesor: ProfesorDTO): void {
    const id = profesor.id;
  
    if (!id) {
      console.error('ID del profesor no definido:', profesor);
      alert('No se puede editar el profesor: ID no definido');
      return;
    }
  
    this.router.navigate(['/editar-profesor', id]);
  }

  eliminarProfesor(profesorId: number): void {
    if (!profesorId) {
      console.error('ID del profesor no válido:', profesorId);
      return;
    }

    if (confirm('¿Seguro que deseas eliminar este profesor?')) {
      this.profesorService.eliminarProfesor(profesorId).subscribe({
        next: () => {
          alert('Profesor eliminado con éxito');
          this.obtenerProfesores();
        },
        error: (err) => {
          console.error('Error al eliminar profesor:', err);
          alert('No se pudo eliminar el profesor');
        }
      });
    }
  }
}

