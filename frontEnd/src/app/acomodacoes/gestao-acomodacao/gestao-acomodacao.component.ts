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
      id: 0,
      numero: 0,
      capacidadePessoas: 0,
      quantidadeCamas: 0,
      valorDiaria: 0,
    },
  ];

  selectedId: number = 0;

  selectedIdBtn(id: number) {
    this.selectedId = id;
  }

  goAdicionarAcomodacao() {
    this._router.navigate(['adicionar-acomodacao']);
  }
  deleteBtn(id: number) {
    console.log('click no botão deletar acomodação');
    console.log(id);

    fetch('http://localhost:8080/acomodacao/' + id, {
      method: 'DELETE',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + sessionStorage.getItem('accessToken'),
      },
    })
      .then((response) => {
        if (!response.ok) {
          return { error: response.status };
        }
        return response;
      })
      .then((response) => {
        console.log(response);
        this.ngOnInit();
      });
  }

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
