import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SignupComponent } from './signup/signup.component';
import { GestaoHospedesComponent } from './hospedes/gestao-hospedes/gestao-hospedes.component';
import { GestaoAcomodacaoComponent } from './acomodacoes/gestao-acomodacao/gestao-acomodacao.component';
import { GestaoReservaComponent } from './gestao-reserva/gestao-reserva.component';
import { AdicionarHospedeComponent } from './hospedes/adicionar-hospede/adicionar-hospede.component';
import { EditarHospedeComponent } from './hospedes/editar-hospede/editar-hospede.component';
import { EditarAcomodacaoComponent } from './acomodacoes/editar-acomodacao/editar-acomodacao.component';
import { AdicionarAcomodacaoComponent } from './acomodacoes/adicionar-acomodacao/adicionar-acomodacao.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'gestao-hospedes', component: GestaoHospedesComponent },
  { path: 'gestao-acomodacao', component: GestaoAcomodacaoComponent },
  { path: 'gestao-reserva', component: GestaoReservaComponent },
  { path: 'adicionar-hospede', component: AdicionarHospedeComponent },
  { path: 'editar-hospede', component: EditarHospedeComponent },
  { path: 'editar-acomodacao', component: EditarAcomodacaoComponent },
  { path: 'adicionar-acomodacao', component: AdicionarAcomodacaoComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
