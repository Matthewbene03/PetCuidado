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
import { ServicoService } from '../../services/servico.service';

@Component({
  selector: 'app-cadastrar-servico',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cadastrar-servico.component.html',
  styleUrl: './cadastrar-servico.component.css'
})
export class CadastrarServicoComponent {

  formulario: FormGroup;
  
  constructor(private fb: FormBuilder, private servicoService: ServicoService, private route: ActivatedRoute, private router: Router) {
    this.formulario = this.fb.group({
      id: [''], // campo opcional para identificar edição
      descricao: ['', Validators.required],
      preco: ['', Validators.required],
      duracao: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.servicoService.buscarPorId(id).subscribe(servico => {
        this.formulario.patchValue(servico)
      });
    }
  }

  onSubmit(): void {
    if (this.formulario.valid) {
        this.servicoService.salvar(this.formulario.value).subscribe(() => {
          alert('Serviço cadastrado com sucesso!');
          this.formulario.reset();
        });
    }
  }
}




