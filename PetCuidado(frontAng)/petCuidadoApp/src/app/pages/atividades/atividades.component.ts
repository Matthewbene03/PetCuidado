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

@Component({
  selector: 'app-atividades',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './atividades.component.html',
  styleUrl: './atividades.component.css'
})
export class AtividadesComponent {

  formulario: FormGroup;
  nomePets: Pet[] = [];
  nomeDonos: Pessoa[] = [];
  listStatus: string[] = ['Não iniciada', 'Em andamento', 'Realizada'];

  cargo: string | null = null;

  constructor(private fb: FormBuilder, private funcionarioService: FuncionarioService, private petService: PetService, private pessoaService: PessoaService, private route: ActivatedRoute, private router: Router){
    this.cargo = this.funcionarioService.getCargoUsuarioLogado();
    if(this.cargo === 'Cuidador'){
      this.formulario = this.fb.group({
        id: [''], // campo opcional para identificar edição
        nomePet: [null, Validators.required],
        nomeDono: [null, Validators.required],
        descricao: ['', Validators.required],
        status: ['', Validators.required]
    });
    } else{
      this.formulario = this.fb.group({
        id: [''], // campo opcional para identificar edição
        nomePet: [null, Validators.required],
        nomeDono: [null, Validators.required],
        tipoSanguineo: ['', Validators.required],
        especie: ['', Validators.required],
        sintomas: ['', Validators.required],
        medicamentos: ['', Validators.required],
        observacao: ['', Validators.required]
    });
    }
  }

    ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.funcionarioService.buscarPorId(id).subscribe(funcionario => {
        this.formulario.patchValue(funcionario)
      });
    }

    this.petService.listar().subscribe(pet =>{
      this.nomePets = pet;
    });

    this.pessoaService.listar().subscribe(pessoa =>{
      this.nomeDonos = pessoa;
    });

  }

  onSubmit(): void {
    if (this.formulario.valid) {
        //this.funcionarioService.salvar(this.formulario.value).subscribe(() => {
          //alert('Funcionario cadastrado com sucesso!');
          //this.formulario.reset();
        //});
    }
  }

  sair(): void{
    this.formulario.reset();
    this.router.navigate(['/menu']);
  }

  entrar(): void{
    alert("Clicou!")
  }
}