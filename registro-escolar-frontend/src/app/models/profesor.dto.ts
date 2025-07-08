// export interface ProfesorDTO {
//   idPersona?: number;
//   nombre: string;
//   apellido: string;
//   email: string;
//   telefono: string;
//   fechaNacimiento: string;
//   especialidad: string;
// }

import { PersonaDTO } from './persona.dto';

export interface ProfesorDTO extends PersonaDTO {
  // idPersona?: number; // Este puede ser redundante si ya usas 'id' de PersonaDTO
  especialidad: string;
  fechaContratacion: string; // formato 'YYYY-MM-DD'
}