import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SignupComponent } from './signup/signup.component';
import { GestaoHospedesComponent } from './gestao-hospedes/gestao-hospedes.component';
import { GestaoAcomodacaoComponent } from './gestao-acomodacao/gestao-acomodacao.component';
import { GestaoReservaComponent } from './gestao-reserva/gestao-reserva.component';
import { AdicionarHospedeComponent } from './adicionar-hospede/adicionar-hospede.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'gestao-hospedes', component: GestaoHospedesComponent },
  { path: 'gestao-acomodacao', component: GestaoAcomodacaoComponent },
  { path: 'gestao-reserva', component: GestaoReservaComponent },
  { path: 'adicionar-hospede', component: AdicionarHospedeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
