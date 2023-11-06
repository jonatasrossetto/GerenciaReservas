import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {
  constructor(private _router: Router) {}

  goGestaoHospedes() {
    this._router.navigate(['gestao-hospedes']);
  }

  goGestaoReserva() {
    this._router.navigate(['gestao-reserva']);
  }

  goGestaoAcomodacao() {
    this._router.navigate(['gestao-acomodacao']);
  }
}
