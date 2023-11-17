import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-adicionar-reserva',
  templateUrl: './adicionar-reserva.component.html',
  styleUrls: ['./adicionar-reserva.component.css'],
})
export class AdicionarReservaComponent {
  constructor(
    private _router: Router,
    private _activatedRoute: ActivatedRoute
  ) {}

  dataEntrada: Date = new Date();
  dataSaida: Date = new Date();
  dataEntradaMin: Date = new Date();
  dataSaidaMin: Date = new Date();
  nomeHospede: string = '';
  numeroAcomodacao: number = 0;
  valorDiaria: number = 0;
  quantidadePessoas: number = 0;

  ajustaDataSaidaMin() {
    const elemento = document.getElementById('dataSaida');
    if (elemento != null) {
      elemento.setAttribute('min', this.dataEntrada.toString());
    }
  }

  goDashBoard() {
    this._router.navigate(['dashboard']);
  }

  goGestaoReserva() {
    this._router.navigate(['gestao-reserva']);
  }

  cadastrarReserva() {
    console.log('cadastrar reserva');
    let hoje = new Date();
    let reserva = {
      acomodacaoId: 11,
      hospedeId: 3,
      usuarioId: 1,
      dataReserva: this.imprimeData(hoje),
      dataEntrada: this.dataEntrada.toString(),
      dataSaida: this.dataSaida.toString(),
      quantidadePessoas: this.quantidadePessoas,
      dataHoraEntrada: null,
      dataHoraSaida: null,
      valorDiaria: this.valorDiaria,
      valorPagoTotal: null,
      formaDePagamento: null,
      observacao: 'observação',
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
  }

  imprimeData(data: Date): string {
    let day = data.getUTCDate();
    let month = data.getUTCMonth() + 1;
    let year = data.getUTCFullYear();
    console.log(`${year}-${month}-${day}`);
    return `${year}-${month}-${day}`;
  }
}
