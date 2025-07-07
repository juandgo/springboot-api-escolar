import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EstudianteDTO } from '../models/estudiante.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EstudianteService {
  private baseUrl = 'http://localhost:8080/api/estudiantes';

  constructor(private http: HttpClient) {}

  registrarEstudiante(estudiante: EstudianteDTO): Observable<EstudianteDTO> {
    return this.http.post<EstudianteDTO>(`${this.baseUrl}/registrar`, estudiante);
  }

  listarEstudiantes(): Observable<EstudianteDTO[]> {
    return this.http.get<EstudianteDTO[]>(this.baseUrl);
  }

  obtenerEstudiantePorId(id: number): Observable<EstudianteDTO> {
    return this.http.get<EstudianteDTO>(`${this.baseUrl}/buscar/id/${id}`);
  }

  buscarPorGrado(grado: string): Observable<EstudianteDTO[]> {
    return this.http.get<EstudianteDTO[]>(`${this.baseUrl}/buscar/grado/${grado}`);
  }

  actualizarEstudiante(estudiante: EstudianteDTO): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/actualizar/${estudiante.id}`, estudiante);
  }

  eliminarEstudiante(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/eliminar/${id}`);
  }
}
