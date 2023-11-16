import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestaoReservaComponent } from './gestao-reserva.component';

describe('GestaoReservaComponent', () => {
  let component: GestaoReservaComponent;
  let fixture: ComponentFixture<GestaoReservaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestaoReservaComponent]
    });
    fixture = TestBed.createComponent(GestaoReservaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
