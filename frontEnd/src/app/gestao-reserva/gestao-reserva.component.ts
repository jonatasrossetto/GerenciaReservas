import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-gestao-reserva',
  templateUrl: './gestao-reserva.component.html',
  styleUrls: ['./gestao-reserva.component.css'],
})
export class GestaoReservaComponent implements OnInit {
  constructor(
    private _router: Router,
    private _activatedRoute: ActivatedRoute
  ) {}

  listaDeReservas = [
    {
      id: 0,
      acomodacao: {
        id: -1,
        numero: -1,
        capacidadePessoas: -1,
        quantidadeCamas: -1,
        valorDiaria: -1,
      },
      hospede: {
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
      },
      usuarioId: 0,
      dataReserva: '2023-10-25',
      dataEntrada: '2023-11-01',
      dataSaida: '2023-11-08',
      quantidadePessoas: 2,
      dataHoraEntrada: null,
      dataHoraSaida: null,
      valorDiaria: 200.0,
      valorPagoTotal: null,
      formaDePagamento: null,
      observacao: 'observacao',
    },
  ];

  acomodacao = {
    id: -1,
    numero: -1,
    capacidadePessoas: -1,
    quantidadeCamas: -1,
    valorDiaria: -1,
  };

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

  selectedId: number = 0;

  editarBtn(id: number) {}
  deleteBtn(id: number) {}

  selectedIdBtn(id: number) {
    this.selectedId = id;
  }

  goDashBoard() {
    this._router.navigate(['dashboard']);
  }
  goAdicionarReserva() {
    this._router.navigate(['adicionar-reserva']);
  }

  ngOnInit(): void {
    const accessToken = sessionStorage.getItem('accessToken');
    console.log('accessToken: ' + accessToken);
    if (accessToken !== null) {
      const authToken = 'Bearer ' + accessToken;
      fetch('http://localhost:8080/reserva', {
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
          this.listaDeReservas = data; // update table view
          this.listaDeReservas.forEach((reserva) => {
            console.log(JSON.stringify(reserva));
          });
        });
    } else {
      console.log('token not found');
      sessionStorage.clear();
      this._router.navigate(['login']);
    }
  }

  recuperaDadosHospede(id: number) {
    fetch('http://localhost:8080/hospede/' + id, {
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
          this.hospede.id = id;
          console.log('hóspede:' + JSON.stringify(this.hospede));
          console.log('Dados do hospede recuperado com sucesso!');
        }
      })
      .catch((e) => {
        console.log('Error:' + e);
      });
  }
  recuperaDadosAcomodacao(id: number) {
    fetch('http://localhost:8080/acomodacao/' + id, {
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
          this.acomodacao.id = id;
          console.log('acomodação:' + JSON.stringify(this.acomodacao));
          console.log('Dados da acomodação recuperados com sucesso!');
        }
      })
      .catch((e) => {
        console.log('Error:' + e);
      });
  }
}
