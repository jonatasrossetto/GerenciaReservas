import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  constructor(private _router: Router) {}

  inputUserEmail: string = '';
  inputUserPassword: string = '';
  tokenData = {};

  clickme() {
    console.log('sign-in.js is running');
    const loginData = {
      login: this.inputUserEmail,
      senha: this.inputUserPassword,
    };
    console.log(JSON.stringify(loginData));

    fetch('http://localhost:8080/login', {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(loginData),
    })
      .then((response) => {
        if (!response.ok) {
          return { error: response.status };
        }
        return response.json();
      })
      .then((response) => {
        if (response.error) {
          console.log('Error: ' + response.error);
          return;
        } else {
          console.log('token: ' + response.token);
          sessionStorage.setItem('accessToken', response.token);
        }
      })
      .catch((e) => {
        console.log('Error:' + e);
      });
  }
}
