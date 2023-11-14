import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdicionarHospedeComponent } from './adicionar-hospede.component';

describe('AdicionarHospedeComponent', () => {
  let component: AdicionarHospedeComponent;
  let fixture: ComponentFixture<AdicionarHospedeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdicionarHospedeComponent]
    });
    fixture = TestBed.createComponent(AdicionarHospedeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
