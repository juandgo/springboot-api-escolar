import { PersonaDTO } from '../models/persona.dto';

export interface EstudianteDTO extends PersonaDTO {
  numeroMatricula: string;
  grado: string;
}
