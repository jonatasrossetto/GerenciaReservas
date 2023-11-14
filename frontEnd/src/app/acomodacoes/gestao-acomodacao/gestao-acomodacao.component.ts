import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-gestao-acomodacao',
  templateUrl: './gestao-acomodacao.component.html',
  styleUrls: ['./gestao-acomodacao.component.css'],
})
export class GestaoAcomodacaoComponent implements OnInit {
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
  goAdicionarAcomodacao() {
    this._router.navigate(['adicionar-acomodacao']);
  }
  deleteBtn(id: number) {}
  editarBtn(id: number) {
    this._router.navigate(['editar-acomodacao'], {
      queryParams: { id: id },
    });
  }
  goDashBoard() {
    this._router.navigate(['dashboard']);
  }
  ngOnInit(): void {
    const accessToken = sessionStorage.getItem('accessToken');
    console.log('accessToken: ' + accessToken);
    if (accessToken !== null) {
      const authToken = 'Bearer ' + accessToken;
      fetch('http://localhost:8080/acomodacao', {
        method: 'GET',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json',
          Authorization: authToken,
        },
      })
        .then((response) => {
          if (!response.ok) {
            console.log('token not authorized');
            sessionStorage.clear();
            this._router.navigate(['login']);
          }
          return response.json();
        })
        .then((data) => {
          this.listaDeAcomodacoes = data; // update table view
          this.listaDeAcomodacoes.forEach((acomodacao) => {
            console.log(JSON.stringify(acomodacao));
          });
        });
    } else {
      console.log('token not found');
      sessionStorage.clear();
      this._router.navigate(['login']);
    }
  }
}
