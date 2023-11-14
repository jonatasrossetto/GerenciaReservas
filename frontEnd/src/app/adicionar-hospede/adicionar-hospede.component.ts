import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-adicionar-hospede',
  templateUrl: './adicionar-hospede.component.html',
  styleUrls: ['./adicionar-hospede.component.css'],
})
export class AdicionarHospedeComponent {
  constructor(
    private _router: Router,
    private _activatedRoute: ActivatedRoute
  ) {}

  nome: string = '';
  numeroDocumento: string = '';
  tipoDocumento: string = '';
  telefone: string = '';
  email: string = '';
  endereco: string = '';
  dataNascimento: Date = new Date();
  goDashBoard() {
    this._router.navigate(['dashboard']);
  }

  cadastrarHospede() {
    console.log('click no botão cadastrar hospede');

    const date = new Date();
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    // This arrangement can be altered based on how we want the date's format to appear.
    let currentDate = `${year}-${month}-${day}`;

    console.log(currentDate); // "17-6-2022"
    let hospede = {
      usuarioId: 1,
      nome: this.nome,
      numeroDocumento: this.numeroDocumento,
      tipoDocumento: this.tipoDocumento,
      telefone: this.telefone,
      email: this.email,
      endereco: this.endereco,
      dataNascimento: this.dataNascimento,
      dataCadastro: currentDate,
    };
    console.log(hospede);

    fetch('http://localhost:8080/hospede', {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + sessionStorage.getItem('accessToken'),
      },
      body: JSON.stringify(hospede),
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
          console.log('Success: ' + response);
          console.log('Hospede cadastrado com sucesso!');
          this._router.navigate(['gestao-hospedes']);
        }
      })
      .catch((e) => {
        console.log('Error:' + e);
      });
  }
}
