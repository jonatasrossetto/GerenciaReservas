import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-adicionar-acomodacao',
  templateUrl: './adicionar-acomodacao.component.html',
  styleUrls: ['./adicionar-acomodacao.component.css'],
})
export class AdicionarAcomodacaoComponent {
  constructor(
    private _router: Router,
    private _activatedRoute: ActivatedRoute
  ) {}

  numero: number = 0;
  capacidade: number = 0;
  camas: number = 0;
  valorDiaria: number = 0;

  cadastrarAcomodacao() {
    console.log('cadastrar acomodacao');
    let acomodacao = {
      numero: this.numero,
      capacidadePessoas: this.capacidade,
      quantidadeCamas: this.camas,
      valorDiaria: this.valorDiaria,
    };
    console.log(acomodacao);
    fetch('http://localhost:8080/acomodacao', {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + sessionStorage.getItem('accessToken'),
      },
      body: JSON.stringify(acomodacao),
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
          console.log('Acomodação cadastrada com sucesso!');
          this._router.navigate(['gestao-acomodacao']);
        }
      })
      .catch((e) => {
        console.log('Error:' + e);
      });
  }

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
}
