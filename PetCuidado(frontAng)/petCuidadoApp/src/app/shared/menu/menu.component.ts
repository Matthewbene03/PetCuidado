import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioService } from '../../services/funcionario.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
  cargo: string | null = null;

  constructor(private funcionarioService: FuncionarioService, private router: Router){
    this.cargo = this.funcionarioService.getCargoUsuarioLogado();
  }

  logout(): void {
    localStorage.removeItem('usuarioLogado'); // ou localStorage.clear()
    this.router.navigate(['']);
  }
}
