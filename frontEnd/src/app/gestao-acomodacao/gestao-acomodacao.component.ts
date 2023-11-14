import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-gestao-acomodacao',
  templateUrl: './gestao-acomodacao.component.html',
  styleUrls: ['./gestao-acomodacao.component.css'],
})
export class GestaoAcomodacaoComponent {
  constructor(
    private _router: Router,
    private _activatedRoute: ActivatedRoute
  ) {}
  listaDeAcomodacoes = [
    {
      id: 3,
      numero: 2,
      capacidadePessoas: 4,
      quantidadeCamas: 2,
      valorDiaria: 200.0,
    },
  ];
  goAdicionarAcomodacao() {}
  deleteBtn(id: number) {}
  editarBtn(id: number) {}
  goDashBoard() {
    this._router.navigate(['dashboard']);
  }
}
