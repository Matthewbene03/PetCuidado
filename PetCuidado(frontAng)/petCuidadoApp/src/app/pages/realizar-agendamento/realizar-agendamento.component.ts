import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { firstValueFrom } from 'rxjs';

import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { FuncionarioService } from '../../services/funcionario.service';
import { Funcionario } from '../../models/funcionario';
import { Servico } from '../../models/servico';
import { Pet } from '../../models/pet';
import { PetService } from '../../services/pet.service';
import { ServicoService } from '../../services/servico.service';
import { AgendamentoService } from '../../services/agendamento.service';

@Component({
  selector: 'app-realizar-agendamento',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './realizar-agendamento.component.html',
  styleUrl: './realizar-agendamento.component.css'
})

export class RealizarAgendamentoComponent {

  formulario: FormGroup;
  dataMinima: string = '';
  servicos: Servico[] = [];
  funcionarios: Funcionario[] = [];
  pets: Pet[] = [];
  enumCargos: string[] = ['Medico_Veterinario', 'Cuidador'];


  constructor(private fb: FormBuilder, private agendamento: AgendamentoService, private funcionarioService: FuncionarioService, private petService: PetService, private servicoService: ServicoService, private route: ActivatedRoute, private router: Router) {
    this.formulario = this.fb.group({
      id: [''], // campo opcional para identificar edição
      data: ['', Validators.required],
      pet: [null, Validators.required],
      funcionario: [null, Validators.required],
      servico: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.funcionarioService.buscarPorId(id).subscribe(funcionario => {
        this.formulario.patchValue(funcionario)
      });
    }

    this.funcionarioService.listar().subscribe(funcionario => {
      this.funcionarios = funcionario.filter(f => f.cargo === this.enumCargos[0] || f.cargo === this.enumCargos[1]);
    });

    this.petService.listar().subscribe(pet => {
      this.pets = pet;
    });

    this.servicoService.listar().subscribe(servico => {
      this.servicos = servico;
    });

    const agora = new Date();
    const ano = agora.getFullYear();
    const mes = String(agora.getMonth() + 1).padStart(2, '0');
    const dia = String(agora.getDate()).padStart(2, '0');
    const hora = String(agora.getHours()).padStart(2, '0');
    const minuto = String(agora.getMinutes()).padStart(2, '0');
    this.dataMinima = `${ano}-${mes}-${dia}T${hora}:${minuto}`;
  }

  onSubmit(): void {
    if (this.formulario.valid) {
      this.agendamento.salvar(this.formulario.value).subscribe(() => {
        alert('Agendamento cadastrado com sucesso!');
        this.formulario.reset();
      });
    }
  }
}




