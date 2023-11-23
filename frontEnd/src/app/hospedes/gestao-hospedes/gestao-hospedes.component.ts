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

  selectedId: number = 0;

  selectedIdBtn(id: number) {
    this.selectedId = id;
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

  goAdicionarHospede() {
    this._router.navigate(['adicionar-hospede']);
  }

  editarBtn(id: number) {
    console.log('click no botão editar hospede');
    console.log(id);
    // this._router.navigate(['editar-hospede'], { queryParams: { id: id } });
    this._router.navigate(['editar-hospede'], {
      queryParams: { id: id },
    });
    // this._router.navigate(['editar-hospede']);
  }

  deleteBtn(id: number) {
    console.log('click no botão deletar hospede');
    console.log(id);

    fetch('http://localhost:8080/hospede/' + id, {
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
