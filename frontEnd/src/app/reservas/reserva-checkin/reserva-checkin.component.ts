import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-reserva-checkin',
  templateUrl: './reserva-checkin.component.html',
  styleUrls: ['./reserva-checkin.component.css'],
})
export class ReservaCheckinComponent implements OnInit {
  constructor(private _router: Router, private route: ActivatedRoute) {}
  id: number = 0; // id da reserva que será editada
  reserva = {
    acomodacaoId: 0,
    hospedeId: 0,
    usuarioId: 0,
    dataReserva: '',
    dataEntrada: '',
    dataSaida: '',
    quantidadePessoas: 0,
    dataHoraEntrada: null,
    dataHoraSaida: null,
    valorDiaria: 0,
    valorPagoTotal: null,
    formaDePagamento: null,
    observacao: null,
  };
  dataEntradaMin: Date = new Date();
  dataSaidaMin: Date = new Date();
  nomeHospede: string = '';
  acomodacao: string = '';
  selectedAcomodacaoId: number = 0;
  selectedHospedeId: number = 0;
  listaDeAcomodacoes = [
    {
      id: 0,
      numero: 0,
      capacidadePessoas: 0,
      quantidadeCamas: 0,
      valorDiaria: 0,
    },
  ];
  listaDeHospedes = [{ id: 0, nome: 'nome' }];
  goGestaoReserva() {
    this._router.navigate(['gestao-reserva']);
  }
  goDashBoard() {
    this._router.navigate(['dashboard']);
  }

  atualizarReserva() {
    console.log('atualizar reserva');
    if (
      this.selectedHospedeId != 0 &&
      this.selectedAcomodacaoId != 0 &&
      this.reserva.dataEntrada != null &&
      this.reserva.dataSaida != null &&
      this.reserva.quantidadePessoas != 0 &&
      this.reserva.valorDiaria != 0
    ) {
      this.reserva.acomodacaoId = this.selectedAcomodacaoId;
      this.reserva.hospedeId = this.selectedHospedeId;
      console.log(this.reserva);
      fetch('http://localhost:8080/reserva', {
        method: 'PUT',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + sessionStorage.getItem('accessToken'),
        },
        body: JSON.stringify(this.reserva),
      })
        .then((response) => {
          return response.json();
        })
        .then((response) => {
          if (response.error) {
            alert('Error: ' + response.error);
            return;
          } else {
            console.log('Success: ' + response);
            alert('Reserva atualizada com sucesso!');
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

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.id = params['id'];
      console.log(this.id);
    });

    const accessToken = sessionStorage.getItem('accessToken');
    console.log('accessToken: ' + accessToken);
    if (accessToken !== null) {
      const authToken = 'Bearer ' + accessToken;
      fetch('http://localhost:8080/reserva/' + this.id, {
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
          this.reserva = data; // update table view
          console.log(this.reserva.dataHoraEntrada);
          this.selectedHospedeId = this.reserva.hospedeId;
          this.selectedAcomodacaoId = this.reserva.acomodacaoId;
          this.atualizaNomeHospede(this.reserva.hospedeId);
          this.atualizaTextoAcomodacao(this.reserva.acomodacaoId);
        });
    } else {
      console.log('token not found');
      sessionStorage.clear();
      this._router.navigate(['login']);
    }
  }
  atualizaNomeHospede(hospedeId: number) {
    this.nomeHospede =
      this.listaDeHospedes.find((hospede) => hospede.id === hospedeId)?.nome ??
      '';
  }
  atualizaTextoAcomodacao(acomodacaoId: number) {
    let acomodacao = this.listaDeAcomodacoes.find(
      (elemento) => elemento.id === acomodacaoId
    );
    if (acomodacao != null) {
      this.acomodacao =
        'Acomodação ' +
        acomodacao.numero.toString() +
        ' para ' +
        acomodacao.capacidadePessoas.toString() +
        ' hóspedes, ' +
        acomodacao.quantidadeCamas.toString() +
        ' cama, R$' +
        acomodacao.valorDiaria.toString() +
        '/diária.';
    }
  }
}
