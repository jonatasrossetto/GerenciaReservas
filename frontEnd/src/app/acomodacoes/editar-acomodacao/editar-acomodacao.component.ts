import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editar-acomodacao',
  templateUrl: './editar-acomodacao.component.html',
  styleUrls: ['./editar-acomodacao.component.css'],
})
export class EditarAcomodacaoComponent implements OnInit {
  constructor(private _router: Router, private route: ActivatedRoute) {}
  id: number = 0;
  acomodacao = {
    id: -1,
    numero: -1,
    capacidadePessoas: -1,
    quantidadeCamas: -1,
    valorDiaria: -1,
  };

  atualizarAcomodacao() {
    console.log('atualizar acomodacao');
    console.log(this.acomodacao);
    fetch('http://localhost:8080/acomodacao', {
      method: 'PUT',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + sessionStorage.getItem('accessToken'),
      },
      body: JSON.stringify(this.acomodacao),
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
          console.log('Acomodação atualizada com sucesso!');
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
  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.id = params['id'];
      console.log(this.id);
    });
    fetch('http://localhost:8080/acomodacao/' + this.id, {
      method: 'GET',
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
        return response.json();
      })
      .then((response) => {
        if (response.error) {
          console.log('Error: ' + response.error);
          return;
        } else {
          this.acomodacao = response;
          this.acomodacao.id = this.id;
          console.log(JSON.stringify(this.acomodacao));
          console.log('Dados da acomodação recuperados com sucesso!');
        }
      })
      .catch((e) => {
        console.log('Error:' + e);
      });
  }
}
