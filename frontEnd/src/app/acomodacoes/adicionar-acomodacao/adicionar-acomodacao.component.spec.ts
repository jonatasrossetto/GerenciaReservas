import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdicionarAcomodacaoComponent } from './adicionar-acomodacao.component';

describe('AdicionarAcomodacaoComponent', () => {
  let component: AdicionarAcomodacaoComponent;
  let fixture: ComponentFixture<AdicionarAcomodacaoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdicionarAcomodacaoComponent]
    });
    fixture = TestBed.createComponent(AdicionarAcomodacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
