import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface PersonaDTO {
  idPersona?: number;
  nombre: string;
  apellido: string;
  email: string;
  telefono: string;
  fechaNacimiento: string;
}

@Injectable({
  providedIn: 'root'
})
export class PersonaService {
  private apiUrl = 'http://localhost:8080/api/personas';

  constructor(private http: HttpClient) {}

  registrarPersona(persona: PersonaDTO): Observable<PersonaDTO> {
    return this.http.post<PersonaDTO>(`${this.apiUrl}/registrar`, persona);
  }

  listarPersonas(): Observable<PersonaDTO[]> {
    return this.http.get<PersonaDTO[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<PersonaDTO> {
    return this.http.get<PersonaDTO>(`${this.apiUrl}/buscar/id/${id}`);
  }

  buscarPorApellido(apellido: string): Observable<PersonaDTO[]> {
    return this.http.get<PersonaDTO[]>(`${this.apiUrl}/buscar/apellido/${apellido}`);
  }

  actualizarPersona(id: number, persona: PersonaDTO): Observable<PersonaDTO> {
    return this.http.put<PersonaDTO>(`${this.apiUrl}/actualizar/${id}`, persona);
  }

  eliminarPersona(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
