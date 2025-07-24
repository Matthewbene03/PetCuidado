import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
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
    this.agendamento.listar().subscribe(agendamentos => {
      this.agendamentos = agendamentos.sort((a, b) =>
        new Date(b.data).getTime() - new Date(a.data).getTime()
      );
    });
  }

  carregarProtudosPet(): void {
    this.agendamentos.sort((a, b) => {
      const nomeA = a.pet.nome.toLowerCase();
      const nomeB = b.pet.nome.toLowerCase();
      return nomeA.localeCompare(nomeB);
    });
  }

  carregarProtudosFuncionario(): void {
    this.agendamentos.sort((a, b) => {
      const nomeA = a.funcionario.pessoa.nome.toLowerCase();
      const nomeB = b.funcionario.pessoa.nome.toLowerCase();
      return nomeA.localeCompare(nomeB);
    });
  }

  carregarProtudosServico(): void {
    this.agendamentos.sort((a, b) => {
      const descA = a.servico.descricao.toLowerCase();
      const descB = b.servico.descricao.toLowerCase();
      return descA.localeCompare(descB);
    });
  }
}