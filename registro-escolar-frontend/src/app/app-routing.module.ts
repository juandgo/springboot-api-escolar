import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegistroProfesorComponent } from './pages/registro-profesor/registro-profesor.component';
import { ListarProfesoresComponent } from './pages/listar-profesores/listar-profesores.component';
import { RegistroEstudianteComponent } from './pages/registro-estudiante/registro-estudiante.component';
import { ListarEstudiantesComponent } from './pages/listar-estudiantes/listar-estudiantes.component';

const routes: Routes = [
  { path: '', redirectTo: 'listar-profesores', pathMatch: 'full' },
  { path: 'registrar-profesor', component: RegistroProfesorComponent },
  { path: 'listar-profesores', component: ListarProfesoresComponent },
  { path: 'editar-profesor/:id', component: RegistroProfesorComponent },
  { path: 'listar-estudiantes', component: ListarEstudiantesComponent },
  { path: 'registrar-estudiante', component: RegistroEstudianteComponent },
  { path: 'editar-estudiante/:id', component: RegistroEstudianteComponent },
  { path: '', redirectTo: '/listar-profesores', pathMatch: 'full' }
  // otras rutas...
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
  // path: 'editar-profesor/:id', component: EditarProfesorComponent 
}
