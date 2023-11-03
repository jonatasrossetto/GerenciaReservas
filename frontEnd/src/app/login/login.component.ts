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
    const authenticationData = {
      login: this.inputUserEmail,
      senha: this.inputUserPassword,
    };
    console.log(JSON.stringify(authenticationData));

    fetch('http://localhost:8080/login', {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(authenticationData),
    })
      .then((data) => {
        if (!data.ok) {
          console.log(data);
          return;
        }
        return data.json();
      })
      .then((teste) => {
        console.log(teste);
      })
      .catch((e) => {
        console.log(e);
      });
  }
}
