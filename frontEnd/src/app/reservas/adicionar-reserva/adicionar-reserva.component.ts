import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-adicionar-reserva',
  templateUrl: './adicionar-reserva.component.html',
  styleUrls: ['./adicionar-reserva.component.css'],
})
export class AdicionarReservaComponent {
  constructor(
    private _router: Router,
    private _activatedRoute: ActivatedRoute
  ) {}

  dataEntrada: Date = new Date();
  dataSaida: Date = new Date();
  dataEntradaMin: Date = new Date();
  dataSaidaMin: Date = new Date();
  nomeHospede: string = '';
  numeroAcomodacao: number = 0;
  valorDiaria: number = 0;
  quantidadePessoas: number = 0;

  ajustaDataSaidaMin() {
    const elemento = document.getElementById('dataSaida');
    if (elemento != null) {
      elemento.setAttribute('min', this.dataEntrada.toString());
    }
  }

  goDashBoard() {
    this._router.navigate(['dashboard']);
  }

  goGestaoReserva() {
    this._router.navigate(['gestao-reserva']);
  }
}
