import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-gestao-hospedes',
  templateUrl: './gestao-hospedes.component.html',
  styleUrls: ['./gestao-hospedes.component.css'],
})
export class GestaoHospedesComponent implements OnInit {
  constructor(
    private _router: Router,
    private _activatedRoute: ActivatedRoute
  ) {}

  listaDeHospedes = [
    {
      id: 0,
      usuarioId: 0,
      numeroDocumento: '',
      tipoDocumento: '',
      nome: '',
      telefone: 0,
      email: '',
      endereco: '',
      dataNascimento: '',
      dataCadastro: '',
    },
  ];

  ngOnInit() {
    const accessToken = sessionStorage.getItem('accessToken');
    console.log('accessToken: ' + accessToken);
    if (accessToken !== null) {
      const authToken = 'Bearer ' + accessToken;
      fetch('http://localhost:8080/hospede', {
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
          this.listaDeHospedes = data; // update table view
          this.listaDeHospedes.forEach((hospede) => {
            console.log(hospede.nome);
          });
        });
    } else {
      console.log('token not found');
      sessionStorage.clear();
      this._router.navigate(['login']);
    }
  }
}
