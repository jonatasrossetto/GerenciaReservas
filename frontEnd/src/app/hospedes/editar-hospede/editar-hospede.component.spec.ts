import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarHospedeComponent } from './editar-hospede.component';

describe('EditarHospedeComponent', () => {
  let component: EditarHospedeComponent;
  let fixture: ComponentFixture<EditarHospedeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditarHospedeComponent]
    });
    fixture = TestBed.createComponent(EditarHospedeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
