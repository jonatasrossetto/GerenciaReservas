import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservaCheckoutComponent } from './reserva-checkout.component';

describe('ReservaCheckoutComponent', () => {
  let component: ReservaCheckoutComponent;
  let fixture: ComponentFixture<ReservaCheckoutComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReservaCheckoutComponent]
    });
    fixture = TestBed.createComponent(ReservaCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
