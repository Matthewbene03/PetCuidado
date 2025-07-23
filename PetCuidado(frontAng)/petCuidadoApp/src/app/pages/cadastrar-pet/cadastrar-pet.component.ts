import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { firstValueFrom } from 'rxjs';

import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { PetService } from '../../services/pet.service';
import { Pessoa } from '../../models/pessoa';
import { PessoaService } from '../../services/pessoa.service';

@Component({
  selector: 'app-cadastrar-pet',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cadastrar-pet.component.html',
  styleUrl: './cadastrar-pet.component.css'
})
export class CadastrarPetComponent {

  formulario: FormGroup;
  pessoas: Pessoa[] = [];

  constructor(private fb: FormBuilder, private petService: PetService, private pessoaService: PessoaService, private route: ActivatedRoute, private router: Router) {
    this.formulario = this.fb.group({
      id: [''], // campo opcional para identificar edição
      nome: ['', Validators.required],
      especie: ['', Validators.required],
      raca: ['', Validators.required],
      idade: ['', Validators.required],
      pessoaDono: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.petService.buscarPorId(id).subscribe(pet => {
        this.formulario.patchValue(pet);
      });
    }

    this.pessoaService.listar().subscribe(pessoa => {
      this.pessoas = pessoa;
    });

  }

  onSubmit(): void {
    if (this.formulario.valid) {
      console.log(this.formulario.value);
      this.petService.salvar(this.formulario.value).subscribe(() => {
        alert('Pet cadastrado com sucesso!');
        this.formulario.reset();
      });
    }
  }
}



