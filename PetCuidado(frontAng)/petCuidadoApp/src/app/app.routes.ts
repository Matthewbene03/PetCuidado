import { Routes } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import { CadastrarFuncionarioComponent } from './pages/cadastrar-funcionario/cadastrar-funcionario.component';
import { CadastrarPessoaComponent } from './pages/cadastrar-pessoa/cadastrar-pessoa.component';
import { CadastrarPetComponent } from './pages/cadastrar-pet/cadastrar-pet.component';
import { CadastrarServicoComponent } from './pages/cadastrar-servico/cadastrar-servico.component';
import { TelaPrincipalComponent } from './pages/tela-principal/tela-principal.component';
import { LoginComponent } from './pages/login/login.component';
import { MenuComponent } from './shared/menu/menu.component';
import { RealizarAgendamentoComponent } from './pages/realizar-agendamento/realizar-agendamento.component';

export const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home', component: HomeComponent },
    {
        path: 'menu', component: MenuComponent,
        children: [
            { path: 'cadastrarFuncionario', component: CadastrarFuncionarioComponent },
            { path: 'cadastrarPessoa', component: CadastrarPessoaComponent },
            { path: 'cadastrarPet', component: CadastrarPetComponent },
            { path: 'cadastrarServico', component: CadastrarServicoComponent },
            { path: 'cadastrarFuncionario/:id', component: CadastrarFuncionarioComponent },
            { path: 'cadastrarPessoa/:id', component: CadastrarPessoaComponent },
            { path: 'cadastrarPet/:id', component: CadastrarPetComponent },
            { path: 'cadastrarServico/:id', component: CadastrarServicoComponent },
            {path: 'realizarAgendamento', component: RealizarAgendamentoComponent},
            { path: 'telaPrincipal', component: TelaPrincipalComponent }
        ]
    }
];