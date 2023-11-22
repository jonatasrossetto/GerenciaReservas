import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  constructor(private _router: Router) {}

  newUserEmail: string = '';
  newUserPassword: string = '';
  newUserPasswordConfirmation: string = '';

  clickme() {
    console.log('sign-up.js is running, botão enviar pressionado');
    if (this.newUserPassword != this.newUserPasswordConfirmation) {
      alert('senhas não conferem');
      console.log('senhas não conferem');
      return;
    }
    const signUpData = {
      login: this.newUserEmail,
      senha: this.newUserPassword,
      tipo: 'user',
    };

    fetch('http://localhost:8080/usuarios', {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(signUpData),
    })
      .then((response) => {
        if (!response.ok) {
          return { error: response.status };
          alert(response);
        }
        return response.json();
      })
      .then((response) => {
        if (response.error) {
          console.log('Error: ' + response.error);
          return;
        } else {
          console.log('signup response');
          console.log('leaving signup page!!!');
          this._router.navigate(['login']);
        }
      })
      .catch((e) => {
        console.log('Error:' + e);
      });
  }
}
