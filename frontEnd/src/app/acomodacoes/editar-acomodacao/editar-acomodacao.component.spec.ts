import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarAcomodacaoComponent } from './editar-acomodacao.component';

describe('EditarAcomodacaoComponent', () => {
  let component: EditarAcomodacaoComponent;
  let fixture: ComponentFixture<EditarAcomodacaoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditarAcomodacaoComponent]
    });
    fixture = TestBed.createComponent(EditarAcomodacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
