import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestaoHospedesComponent } from './gestao-hospedes.component';

describe('GestaoHospedesComponent', () => {
  let component: GestaoHospedesComponent;
  let fixture: ComponentFixture<GestaoHospedesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestaoHospedesComponent]
    });
    fixture = TestBed.createComponent(GestaoHospedesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
