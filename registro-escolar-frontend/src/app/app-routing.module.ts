import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegistroProfesorComponent } from './pages/registro-profesor/registro-profesor.component';
import { ListarProfesoresComponent } from './pages/listar-profesores/listar-profesores.component';

const routes: Routes = [
  { path: '', redirectTo: 'listar-profesores', pathMatch: 'full' },
  { path: 'registrar-profesor', component: RegistroProfesorComponent },
  { path: 'listar-profesores', component: ListarProfesoresComponent },
  // otras rutas...
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
  // path: 'editar-profesor/:id', component: EditarProfesorComponent 
}
