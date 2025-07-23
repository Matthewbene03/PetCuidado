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

@Component({
  selector: 'app-cadastrar-funcionario',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cadastrar-funcionario.component.html',
  styleUrl: './cadastrar-funcionario.component.css'
})

export class CadastrarFuncionarioComponent {

  formulario: FormGroup;

  constructor(private fb: FormBuilder, private funcionarioService: FuncionarioService, private pessoaService: PessoaService private route: ActivatedRoute, private router: Router) {
    this.formulario = this.fb.group({
      id: [''], // campo opcional para identificar edição
      nome: ['', Validators.required],
      cpf: ['', Validators.required],
      email: ['', Validators.required],
      telefone: ['', Validators.required],
      cargo: ['', Validators.required],
      usuario: ['', Validators.required],
      senha: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.funcionarioService.buscarPorId(id).subscribe(funcionario => {
        this.formulario.patchValue(funcionario)
      });
    }
  }

  onSubmit(): void {
    if (this.formulario.valid) {
      let pessoa = new Pessoa();
      pessoa.nome = this.formulario.get('nome')?.value;
      pessoa.cpf = this.formulario.get('cpf')?.value;
      pessoa.email = this.formulario.get('email')?.value;
      pessoa.telefone = this.formulario.get('telefone')?.value;

      this.pessoaService.salvar(pessoa).subscribe(() =>{
        alert("Pessoa cadastrada com sucesso!");
      });


      let funcionario = new Funcionario();
      funcionario.cargo = this.formulario.get('cargo')?.value;
      funcionario.usuario = this.formulario.get('usuario')?.value;
      funcionario.senha = this.formulario.get('senha')?.value;
      funcionario.pessoa = pessoa;

      this.funcionarioService.salvar(funcionario).subscribe(() => {
          alert('Funcionario cadastrado com sucesso!');
          this.formulario.reset();
        });
      }
    }
}




