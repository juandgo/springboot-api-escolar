import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatButtonModule} from '@angular/material/button';

import { NavbarComponent } from './components/navbar/navbar.component';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';
import { RegistroProfesorComponent } from './pages/registro-profesor/registro-profesor.component';
import { RegistroEstudianteComponent } from './pages/registro-estudiante/registro-estudiante.component';
import { RegistroPersonaComponent } from './pages/registro-persona/registro-persona.component';

import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { ListarProfesoresComponent } from './pages/listar-profesores/listar-profesores.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SignupComponent,
    LoginComponent,
    RegistroPersonaComponent,
    RegistroProfesorComponent,
    RegistroEstudianteComponent,
    RegistroPersonaComponent,
    ListarProfesoresComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatSnackBarModule,
    MatCardModule,
    MatToolbarModule, 
    MatIconModule,
    MatTableModule,
    HttpClientModule, // Import HttpClientModule for HTTP requests
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
