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
  goDashBoard(event: Event) {
    event.preventDefault();
    this._router.navigate(['dashboard']);
  }

  goGestaoHospedes(event: Event) {
    event.preventDefault();
    this._router.navigate(['gestao-hospedes']);
  }

  goGestaoReserva(event: Event) {
    event.preventDefault();
    this._router.navigate(['gestao-reserva']);
  }

  goGestaoAcomodacao(event: Event) {
    event.preventDefault();
    this._router.navigate(['gestao-acomodacao']);
  }

  cadastrarHospede() {
    console.log('click no botão cadastrar hospede');

    let currentDate = this.imprimeData(new Date());
    console.log(this.dataNascimento);

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

  imprimeData(data: Date): string {
    let day = data.getUTCDate();
    let month = data.getUTCMonth() + 1;
    let year = data.getUTCFullYear();
    console.log(`${year}-${month}-${day}`);
    return `${year}-${month}-${day}`;
  }
}
