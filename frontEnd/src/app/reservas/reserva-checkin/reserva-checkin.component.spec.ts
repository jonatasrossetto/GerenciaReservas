import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservaCheckinComponent } from './reserva-checkin.component';

describe('ReservaCheckinComponent', () => {
  let component: ReservaCheckinComponent;
  let fixture: ComponentFixture<ReservaCheckinComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReservaCheckinComponent]
    });
    fixture = TestBed.createComponent(ReservaCheckinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
