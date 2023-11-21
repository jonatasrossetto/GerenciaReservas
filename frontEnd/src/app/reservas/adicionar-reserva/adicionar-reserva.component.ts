import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-adicionar-reserva',
  templateUrl: './adicionar-reserva.component.html',
  styleUrls: ['./adicionar-reserva.component.css'],
})
export class AdicionarReservaComponent implements OnInit {
  constructor(
    private _router: Router,
    private _activatedRoute: ActivatedRoute
  ) {}

  selectedAcomodacaoId: number = 0;
  selectedHospedeId: number = 0;
  dataEntrada: Date = new Date();
  dataSaida: Date = new Date();
  dataEntradaMin: Date = new Date();
  dataSaidaMin: Date = new Date();
  nomeHospede: string = '';
  acomodacao: string = '';
  valorDiaria: number = 0;
  quantidadePessoas: number = 0;
  observacao: string = '';

  ajustaDataSaidaMin() {
    const elemento = document.getElementById('dataSaida');
    if (elemento != null) {
      elemento.setAttribute('min', this.dataEntrada.toString());
    }
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

  cadastrarReserva() {
    console.log('cadastrar reserva');
    if (
      this.selectedHospedeId != 0 &&
      this.selectedAcomodacaoId != 0 &&
      this.dataEntrada != null &&
      this.dataSaida != null &&
      this.quantidadePessoas != 0 &&
      this.valorDiaria != 0
    ) {
      let hoje = new Date();
      let reserva = {
        acomodacaoId: this.selectedAcomodacaoId,
        hospedeId: this.selectedHospedeId,
        usuarioId: 0,
        dataReserva: this.imprimeData(hoje),
        dataEntrada: this.dataEntrada.toString(),
        dataSaida: this.dataSaida.toString(),
        quantidadePessoas: this.quantidadePessoas,
        dataHoraEntrada: null,
        dataHoraSaida: null,
        valorDiaria: this.valorDiaria,
        valorPagoTotal: null,
        formaDePagamento: null,
        observacao: this.observacao,
      };
      console.log(reserva);
      fetch('http://localhost:8080/reserva', {
        method: 'POST',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + sessionStorage.getItem('accessToken'),
        },
        body: JSON.stringify(reserva),
      })
        .then((response) => {
          return response.json();
        })
        .then((response) => {
          if (response.error) {
            console.log('Error: ' + response.error);
            return;
          } else {
            console.log('Success: ' + response);
            console.log('Reserva cadastrada com sucesso!');
            this._router.navigate(['gestao-reserva']);
          }
        })
        .catch((e) => {
          console.log('Error:' + e);
        });
    } else {
      alert('Preencha todos os campos!');
    }
  }

  imprimeData(data: Date): string {
    let day = data.getUTCDate();
    let month = data.getUTCMonth() + 1;
    let year = data.getUTCFullYear();
    console.log(`${year}-${month}-${day}`);
    return `${year}-${month}-${day}`;
  }

  ngOnInit(): void {
    console.log('ngOnInit');
    let listaDeHospedes = [{ id: 0, nome: 'nome' }];
    let listaDeAcomodacoes = [
      {
        id: 0,
        numero: 0,
        capacidadePessoas: 0,
        quantidadeCamas: 0,
        valorDiaria: 0,
      },
    ];
    const accessToken = sessionStorage.getItem('accessToken');
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
          listaDeHospedes = data;
          let select = document.getElementById('listaHospedes');
          if (select != null) {
            for (let i = 0; i < listaDeHospedes.length; i++) {
              let option = document.createElement('option');
              option.value = listaDeHospedes[i].id.toString();
              option.text = listaDeHospedes[i].nome;
              select.appendChild(option);
            }
          }
          return data;
        });
    } else {
      console.log('token not found');
      sessionStorage.clear();
      this._router.navigate(['login']);
    }

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
          listaDeAcomodacoes = data; // update table view
          let select = document.getElementById('listaAcomodacoes');
          if (select != null) {
            for (let i = 0; i < listaDeAcomodacoes.length; i++) {
              let option = document.createElement('option');
              option.value = listaDeAcomodacoes[i].id.toString();
              option.text =
                'Acomodação ' +
                listaDeAcomodacoes[i].numero.toString() +
                ' para ' +
                listaDeAcomodacoes[i].capacidadePessoas.toString() +
                ' hóspedes, ' +
                listaDeAcomodacoes[i].quantidadeCamas.toString() +
                ' cama, R$' +
                listaDeAcomodacoes[i].valorDiaria.toString() +
                '/diária.';
              select.appendChild(option);
            }
          }
          return data;
        });
    } else {
      console.log('token not found');
      sessionStorage.clear();
      this._router.navigate(['login']);
    }
  }

  escolherHospede() {
    let select = document.getElementById('listaHospedes') as HTMLSelectElement;
    if (select != null) {
      let option = select.options[select.selectedIndex];
      this.selectedHospedeId = parseInt(option.value);
      this.nomeHospede = option.text;
    }
    console.log('selectedHospedeId: ' + this.selectedHospedeId);
    console.log('nomeHospede: ' + this.nomeHospede);
  }
  escolherAcomodacao() {
    let select = document.getElementById(
      'listaAcomodacoes'
    ) as HTMLSelectElement;
    if (select != null) {
      let option = select.options[select.selectedIndex];
      this.selectedAcomodacaoId = parseInt(option.value);
      this.acomodacao = option.text;
    }
    console.log('selectedAcomodacaoId: ' + this.selectedAcomodacaoId);
  }
}
