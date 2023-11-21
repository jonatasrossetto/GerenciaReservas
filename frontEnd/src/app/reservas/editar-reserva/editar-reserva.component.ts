import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editar-reserva',
  templateUrl: './editar-reserva.component.html',
  styleUrls: ['./editar-reserva.component.css'],
})
export class EditarReservaComponent implements OnInit {
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

  ajustaDataSaidaMin() {
    const elemento = document.getElementById('dataSaida');
    if (elemento != null) {
      elemento.setAttribute('min', this.reserva.dataEntrada.toString());
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

  atualizaListaDeHospedes() {
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
          this.listaDeHospedes = data;
          let select = document.getElementById('listaHospedes');
          if (select != null) {
            for (let i = 0; i < this.listaDeHospedes.length; i++) {
              let option = document.createElement('option');
              option.value = this.listaDeHospedes[i].id.toString();
              option.text = this.listaDeHospedes[i].nome;
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
  atualizarListaDeAcomodacoes() {
    const accessToken = sessionStorage.getItem('accessToken');
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
          let select = document.getElementById('listaAcomodacoes');
          if (select != null) {
            for (let i = 0; i < this.listaDeAcomodacoes.length; i++) {
              let option = document.createElement('option');
              option.value = this.listaDeAcomodacoes[i].id.toString();
              option.text =
                'Acomodação ' +
                this.listaDeAcomodacoes[i].numero.toString() +
                ' para ' +
                this.listaDeAcomodacoes[i].capacidadePessoas.toString() +
                ' hóspedes, ' +
                this.listaDeAcomodacoes[i].quantidadeCamas.toString() +
                ' cama, R$' +
                this.listaDeAcomodacoes[i].valorDiaria.toString() +
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
  imprimeData(data: Date): string {
    let day = data.getUTCDate();
    let month = data.getUTCMonth() + 1;
    let year = data.getUTCFullYear();
    console.log(`${year}-${month}-${day}`);
    return `${year}-${month}-${day}`;
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.id = params['id'];
      console.log(this.id);
    });
    this.atualizaListaDeHospedes();
    this.atualizarListaDeAcomodacoes();

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
          console.log(this.reserva);
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
}
