import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestaoAcomodacaoComponent } from './gestao-acomodacao.component';

describe('GestaoAcomodacaoComponent', () => {
  let component: GestaoAcomodacaoComponent;
  let fixture: ComponentFixture<GestaoAcomodacaoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestaoAcomodacaoComponent]
    });
    fixture = TestBed.createComponent(GestaoAcomodacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
