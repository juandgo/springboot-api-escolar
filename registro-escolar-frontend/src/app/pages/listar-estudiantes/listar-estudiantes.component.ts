import { Component, OnInit } from '@angular/core';
import { EstudianteDTO } from '../../models/estudiante.dto';
import { EstudianteService } from '../../services/estudiante.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

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
  
    Swal.fire({
      title: '¿Estás seguro?',
      text: '¿Seguro que deseas eliminar este estudiante?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) => {
      if (result.isConfirmed) {
        this.estudianteService.eliminarEstudiante(id).subscribe({
          next: () => {
            Swal.fire('Estudiante eliminado', 'Estudiante eliminado con éxito', 'info');
            this.obtenerEstudiantes();
          },
          error: (err) => {
            console.error('Error al eliminar estudiante:', err);
            Swal.fire('Error', 'No se pudo eliminar el estudiante', 'error');
          }
        });
      }
    });
    
  }
}
