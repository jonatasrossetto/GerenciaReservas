import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {
  constructor(private _router: Router) {}

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
}
