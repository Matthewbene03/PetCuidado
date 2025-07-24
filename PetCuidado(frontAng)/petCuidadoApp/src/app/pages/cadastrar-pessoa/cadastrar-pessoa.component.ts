import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { firstValueFrom } from 'rxjs';

import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Pessoa } from '../../models/pessoa';
import { PessoaService } from '../../services/pessoa.service';

@Component({
  selector: 'app-cadastrar-pessoa',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cadastrar-pessoa.component.html',
  styleUrl: './cadastrar-pessoa.component.css'
})

export class CadastrarPessoaComponent {

  formulario: FormGroup;

  constructor(private fb: FormBuilder, private pessoaService: PessoaService, private route: ActivatedRoute, private router: Router) {
    this.formulario = this.fb.group({
      id: [''], // campo opcional para identificar edição
      nome: ['', Validators.required],
      cpf: ['', Validators.required],
      email: ['', Validators.required],
      telefone: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.pessoaService.buscarPorId(id).subscribe(pessoa => {
        this.formulario.patchValue(pessoa);
      });
    }
  }

  onSubmit(): void {
    if (this.formulario.valid) {
      let cpf = this.formulario.get('cpf')?.value;

      this.pessoaService.existsByCpf(cpf).subscribe((existe: boolean) => {
        if (!existe) {
          this.pessoaService.salvar(this.formulario.value).subscribe(() => {
            alert('Pessoa cadastrada com sucesso!');
            this.formulario.reset();
          });
        } else {
          alert("Não pode ter mais de uma pessoa com o mesmo CPF!")
        }
      });
    }
  }
}
