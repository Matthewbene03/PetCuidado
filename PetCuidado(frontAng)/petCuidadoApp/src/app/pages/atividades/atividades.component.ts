import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { firstValueFrom } from 'rxjs';

import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { FuncionarioService } from '../../services/funcionario.service';
import { Pessoa } from '../../models/pessoa';
import { Funcionario } from '../../models/funcionario';
import { PessoaService } from '../../services/pessoa.service';
import { Pet } from '../../models/pet';
import { PetService } from '../../services/pet.service';
import { Agendamento } from '../../models/agendamento';

@Component({
  selector: 'app-atividades',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './atividades.component.html',
  styleUrl: './atividades.component.css'
})
export class AtividadesComponent {

  formulario: FormGroup;
  agendamentoRecebido: Agendamento = new Agendamento();
  nomePets: Pet[] = [];
  nomeDonos: Pessoa[] = [];
  listStatus: string[] = ['Não iniciada', 'Em andamento', 'Realizada'];

  cargo: string | null = null;

  constructor(private fb: FormBuilder, private funcionarioService: FuncionarioService, private petService: PetService, private pessoaService: PessoaService, private route: ActivatedRoute, private router: Router) {
    this.cargo = this.funcionarioService.getCargoUsuarioLogado();
    this.formulario = this.fb.group({
      id: [''], // campo opcional para identificar edição
      nomePet: [null, Validators.required],
      nomeDono: [null, Validators.required],
      descricao: ['', Validators.required],
      status: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.funcionarioService.buscarPorId(id).subscribe(funcionario => {
        this.formulario.patchValue(funcionario)
      });
    }

    this.agendamentoRecebido = history.state.agendamento;
    if (this.agendamentoRecebido) {
      this.preencherFormulario(this.agendamentoRecebido);
        this.formulario.get('descricao')?.disable();
    } else {
      this.petService.listar().subscribe(pet => {
        this.nomePets = pet;
      });

      this.pessoaService.listar().subscribe(pessoa => {
        this.nomeDonos = pessoa;
      });
    }
  }

  onSubmit(): void {
    if (this.formulario.valid) {
      //this.funcionarioService.salvar(this.formulario.value).subscribe(() => {
      //alert('Funcionario cadastrado com sucesso!');
      //this.formulario.reset();
      //});
    }
  }

  preencherFormulario(agendamento: Agendamento): void {
    console.log('agendamento recebido:', agendamento);
    console.log('pessoa dono:', agendamento.pet.pessoa);
    this.formulario.patchValue({
      nomePet: agendamento.pet,
      nomeDono: agendamento.pet.pessoa,
      descricao: agendamento.servico.descricao,
      status: this.listStatus[0]
    });
  }

  sair(): void {
    this.formulario.reset();
    this.router.navigate(['/menu']);
  }
}