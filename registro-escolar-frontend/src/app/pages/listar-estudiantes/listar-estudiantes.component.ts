import { Component, OnInit } from '@angular/core';
import { EstudianteDTO } from '../../models/estudiante.dto';
import { EstudianteService } from '../../services/estudiante.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-estudiantes',
  standalone: false,
  templateUrl: './listar-estudiantes.component.html',
  styleUrls: ['./listar-estudiantes.component.css']
})
export class ListarEstudiantesComponent implements OnInit {
  estudiantes: EstudianteDTO[] = [];
  columnas: string[] = ['nombre', 'apellido', 'email', 'telefono', 'carrera', 'acciones'];

  constructor(private estudianteService: EstudianteService, private router: Router) {}

  ngOnInit(): void {
    this.obtenerEstudiantes();
  }

  obtenerEstudiantes(): void {
    this.estudianteService.listarEstudiantes().subscribe({
      next: (data) => this.estudiantes = data,
      error: (err) => console.error('Error al obtener estudiantes:', err)
    });
  }

  editarEstudiante(estudiante: EstudianteDTO): void {
      this.router.navigate(['/editar-estudiante', estudiante.id]);
  }
  

  eliminarEstudiante(id: number | undefined): void {
    console.log('ID recibido para eliminar:', id); // <-- Esto
    if (!id) {
      alert('ID de estudiante no válido');
      return;
    }
  
    if (confirm('¿Seguro que deseas eliminar este estudiante?')) {
      this.estudianteService.eliminarEstudiante(id).subscribe({
        next: () => {
          alert('Estudiante eliminado con éxito');
          this.obtenerEstudiantes();
        },
        error: (err) => {
          console.error('Error al eliminar estudiante:', err);
          alert('No se pudo eliminar el estudiante');
        }
      });
    }
  }
}
