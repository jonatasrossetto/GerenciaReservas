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

  cadastrarAcomodacao() {
    console.log('cadastrar acomodacao');
  }

  goDashBoard() {
    this._router.navigate(['dashboard']);
  }
}
