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
  pessoas: Pessoa[] = [];
  enumCargos: string[] = ['Gerente', 'Medico_Veterinario', 'Cuidador', 'Secretaria'];


  constructor(private fb: FormBuilder, private funcionarioService: FuncionarioService, private pessoaService: PessoaService, private route: ActivatedRoute, private router: Router) {
    this.formulario = this.fb.group({
      id: [''], // campo opcional para identificar edição
      pessoa: [null, Validators.required],
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

    this.pessoaService.listar().subscribe(pessoa => {
      this.pessoas = pessoa;
    });
  }

  onSubmit(): void {
    if (this.formulario.valid) {
        this.funcionarioService.salvar(this.formulario.value).subscribe(() => {
          alert('Funcionario cadastrado com sucesso!');
          this.formulario.reset();
        });
    }
  }
}




