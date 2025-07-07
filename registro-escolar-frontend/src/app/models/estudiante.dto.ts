import { PersonaDTO } from '../models/persona.dto';

export interface EstudianteDTO extends PersonaDTO {
  idPersona?: number; // Este puede ser redundante si ya usas 'id' de PersonaDTO
  numeroMatricula: string;
  grado: string;
}
