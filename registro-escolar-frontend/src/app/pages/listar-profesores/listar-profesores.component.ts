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
    this.profesorService.listarProfesores().subscribe({
      next: (data) => this.profesores = data,
      error: (err) => console.error('Error al obtener profesores:', err)
    });
  }

  editarProfesor(profesor: ProfesorDTO): void {
    // Redireccionar a un componente de edición con el id
    this.router.navigate(['/editar-profesor', profesor.idPersona]);
  }

  // eliminarProfesor(id: number | undefined): void {
  //   if (!id) return;

  //   if (confirm('¿Estás seguro de eliminar este profesor?')) {
  //     this.profesorService.eliminarProfesor(id).subscribe({
  //       next: () => {
  //         alert('Profesor eliminado');
  //         this.obtenerProfesores(); // Recarga la tabla
  //       },
  //       error: (err) => {
  //         console.error('Error al eliminar:', err);
  //         alert('No se pudo eliminar el profesor');
  //       }
  //     });
  //   }
  // }
  eliminarProfesor(id: number): void {
    if (confirm('¿Seguro que deseas eliminar este profesor?')) {
      this.profesorService.eliminarProfesor(id).subscribe({
        next: () => {
          alert('Profesor eliminado');
          this.obtenerProfesores(); // refresca la tabla
        },
        error: err => {
          alert('Error al eliminar');
          console.error(err);
        }
      });
    }
  }
}

