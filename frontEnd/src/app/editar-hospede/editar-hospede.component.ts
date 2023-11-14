import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editar-hospede',
  templateUrl: './editar-hospede.component.html',
  styleUrls: ['./editar-hospede.component.css'],
})
export class EditarHospedeComponent implements OnInit {
  id: number = 0;
  hospede = {
    id: 0,
    usuarioId: 0,
    nome: '',
    numeroDocumento: '',
    tipoDocumento: '',
    telefone: '',
    email: '',
    endereco: '',
    dataNascimento: '',
    dataCadastro: '',
  };

  constructor(private _router: Router, private route: ActivatedRoute) {}

  goDashBoard() {
    this._router.navigate(['dashboard']);
  }

  goGestaoHospedes() {
    this._router.navigate(['gestao-hospedes']);
  }

  atualizarHospede() {
    console.log('atualizar hospede');
    console.log(this.hospede);
    fetch('http://localhost:8080/hospede', {
      method: 'PUT',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + sessionStorage.getItem('accessToken'),
      },
      body: JSON.stringify(this.hospede),
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
          console.log('Hospede atualizado com sucesso!');
          // this.goDashBoard();
        }
      })
      .catch((e) => {
        console.log('Error:' + e);
      });
  }

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.id = params['id'];
      console.log(this.id);
    });

    fetch('http://localhost:8080/hospede/' + this.id, {
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
          this.hospede = response;
          this.hospede.id = this.id;
          console.log(JSON.stringify(this.hospede));
          console.log('Dados do hospede recuperado com sucesso!');
        }
      })
      .catch((e) => {
        console.log('Error:' + e);
      });
  }
}
