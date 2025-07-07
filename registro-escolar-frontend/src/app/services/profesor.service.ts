// src/app/services/profesor.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProfesorDTO } from '../models/profesor.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfesorService {
  private baseUrl = 'http://localhost:8080/api/profesores';

  constructor(private http: HttpClient) {}

  registrarProfesor(profesor: ProfesorDTO): Observable<ProfesorDTO> {
    return this.http.post<ProfesorDTO>(`${this.baseUrl}/registrar`, profesor);
  }

  listarProfesores(): Observable<ProfesorDTO[]> {
    return this.http.get<ProfesorDTO[]>(this.baseUrl);
  }

  obtenerProfesorPorId(id: number): Observable<ProfesorDTO> {
    return this.http.get<ProfesorDTO>(`${this.baseUrl}/buscar/id/${id}`);
  }

  buscarPorEspecialidad(especialidad: string): Observable<ProfesorDTO[]> {
    return this.http.get<ProfesorDTO[]>(`${this.baseUrl}/buscar/especialidad/${especialidad}`);
  }

  // actualizarProfesor(id: number, profesor: ProfesorDTO): Observable<ProfesorDTO> {
  //   return this.http.put<ProfesorDTO>(`${this.baseUrl}/actualizar/${id}`, profesor);
  // }

  actualizarProfesor(profesor: ProfesorDTO): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/actualizar/${profesor.idPersona}`, profesor);
  }

  eliminarProfesor(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/eliminar/${id}`);
  }
}
