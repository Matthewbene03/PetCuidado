import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerificarHorarioComponent } from './verificar-horario.component';

describe('VerificarHorarioComponent', () => {
  let component: VerificarHorarioComponent;
  let fixture: ComponentFixture<VerificarHorarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerificarHorarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VerificarHorarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
