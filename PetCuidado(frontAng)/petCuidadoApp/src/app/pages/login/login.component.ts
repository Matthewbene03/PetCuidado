import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { firstValueFrom } from 'rxjs';

import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { FuncionarioService } from '../../services/funcionario.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {

  formulario: FormGroup;

  constructor(private fb: FormBuilder, private funcionarioService: FuncionarioService, private route: ActivatedRoute, private router: Router) {
    this.formulario = this.fb.group({
      usuario: ['', Validators.required],
      senha: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.funcionarioService.buscarPorId(id).subscribe(funcionario => {
        this.formulario.patchValue(funcionario);
      });
    }
  }

  onSubmit(): void {
    if (this.formulario.valid) {
      const { usuario, senha } = this.formulario.value;

      this.funcionarioService.autenticar(usuario, senha).subscribe({
        next: (funcionario) => {
          localStorage.setItem('usuarioLogado', JSON.stringify(funcionario));

          alert('Login feito com sucesso');
          this.router.navigate(['/menu']);
        },
        error: (err) => {
          console.error('Erro ao autenticar:', err);
          alert('Usuário ou senha inválidos.');
        }
      });
    }
  }
}



