import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SignupComponent } from './signup/signup.component';
import { GestaoHospedesComponent } from './hospedes/gestao-hospedes/gestao-hospedes.component';
import { GestaoAcomodacaoComponent } from './acomodacoes/gestao-acomodacao/gestao-acomodacao.component';
import { GestaoReservaComponent } from './gestao-reserva/gestao-reserva.component';
import { AdicionarHospedeComponent } from './hospedes/adicionar-hospede/adicionar-hospede.component';
import { EditarHospedeComponent } from './hospedes/editar-hospede/editar-hospede.component';
import { AdicionarAcomodacaoComponent } from './acomodacoes/adicionar-acomodacao/adicionar-acomodacao.component';
import { EditarAcomodacaoComponent } from './acomodacoes/editar-acomodacao/editar-acomodacao.component';
import { EditarReservaComponent } from './editar-reserva/editar-reserva.component';
import { AdicionarReservaComponent } from './adicionar-reserva/adicionar-reserva.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    SignupComponent,
    GestaoHospedesComponent,
    GestaoAcomodacaoComponent,
    GestaoReservaComponent,
    AdicionarHospedeComponent,
    EditarHospedeComponent,
    AdicionarAcomodacaoComponent,
    EditarAcomodacaoComponent,
    EditarReservaComponent,
    AdicionarReservaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
