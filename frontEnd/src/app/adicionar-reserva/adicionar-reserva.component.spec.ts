import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdicionarReservaComponent } from './adicionar-reserva.component';

describe('AdicionarReservaComponent', () => {
  let component: AdicionarReservaComponent;
  let fixture: ComponentFixture<AdicionarReservaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdicionarReservaComponent]
    });
    fixture = TestBed.createComponent(AdicionarReservaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
