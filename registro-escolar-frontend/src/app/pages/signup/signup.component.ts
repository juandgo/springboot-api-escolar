import { UserService } from './../../services/user.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-signup',
  standalone: false,
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  public user = {
    name: '',
    lastname: '',
    email: '',
    telefono: '',
    password: '',
    confirmPassword: '',
  };

  constructor(private userService:UserService) {}

  ngOnInit(): void {}

  formSubmit() {
    console.log(this.user);
    if (this.user.password !== this.user.confirmPassword) {
      alert('Las contraseñas no coinciden');
      return;
    }
    this.userService.addUser(this.user).subscribe(
      (data)=>{
        console.log(data);
        alert('Usuario registrado correctamente');
      },(error)=>{
        console.log(error);
        alert('Error al registrar el usuario');
      }
    );
  }
}
