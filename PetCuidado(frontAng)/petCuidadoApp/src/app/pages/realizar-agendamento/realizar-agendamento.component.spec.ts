import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RealizarAgendamentoComponent } from './realizar-agendamento.component';

describe('RealizarAgendamentoComponent', () => {
  let component: RealizarAgendamentoComponent;
  let fixture: ComponentFixture<RealizarAgendamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RealizarAgendamentoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RealizarAgendamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
