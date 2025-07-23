import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router} from '@angular/router';
import { Agendamento } from '../../models/agendamento';
import { AgendamentoService } from '../../services/agendamento.service';

@Component({
  selector: 'app-lista-agendamentos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './lista-agendamentos.component.html',
  styleUrl: './lista-agendamentos.component.css'
})
export class ListaAgendamentosComponent {

  agendamentos: Agendamento[] = [];

  constructor(private agendamento: AgendamentoService, private router: Router) { }

  ngOnInit(): void {
    this.carregarProtudos();
  };

  carregarProtudos(): void {
    this.agendamento.listar().subscribe(agendamento => {
      this.agendamentos = agendamento;
    });
  }
}
