import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Agendamento } from '../../models/agendamento';
import { AgendamentoService } from '../../services/agendamento.service';
import { FuncionarioService } from '../../services/funcionario.service';

@Component({
  selector: 'app-verificar-horario',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './verificar-horario.component.html',
  styleUrl: './verificar-horario.component.css'
})
export class VerificarHorarioComponent {

  agendamentos: Agendamento[] = [];

  constructor(private agendamento: AgendamentoService, private funcionario: FuncionarioService, private router: Router) { }

  ngOnInit(): void {
    this.carregarProtudos();
  };

  carregarProtudos(): void {
    const funcionarioId = this.funcionario.getIdUsuarioLogado();
    const hoje = new Date();

    // Começo da semana (domingo)
    const inicioSemana = new Date(hoje);
    inicioSemana.setDate(hoje.getDate() - hoje.getDay());
    inicioSemana.setHours(0, 0, 0, 0);

    // Fim da semana (sábado)
    const fimSemana = new Date(hoje);
    fimSemana.setDate(hoje.getDate() + (6 - hoje.getDay()));
    fimSemana.setHours(23, 59, 59, 999);

    this.agendamento.listar().subscribe(agendamentos => {
      const filtrados = agendamentos.filter(a =>
        a.funcionario.id === funcionarioId &&
        new Date(a.data) >= inicioSemana &&
        new Date(a.data) <= fimSemana &&
        a.status !== 'CONCLUIDO'
      );

      this.agendamentos = filtrados.sort((a, b) =>
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

  carregarProtudosDono(): void {
    this.agendamentos.sort((a, b) => {
      const nomeA = a.pet.pessoa.nome.toLowerCase();
      const nomeB = b.pet.pessoa.nome.toLowerCase();
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

  executarAcao(agendamento: Agendamento): void {
    this.router.navigate(['/menu/atividades'], { state: { agendamento: agendamento } });
  }
}