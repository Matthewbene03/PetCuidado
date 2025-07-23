import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pessoa } from '../models/pessoa';

@Injectable({
  providedIn: 'root'
})

export class PessoaService {

  private apiUrl = 'http://localhost:8080/pessoa';

  constructor(private http: HttpClient) { }

  listar(): Observable<Pessoa[]> {
    return this.http.get<Pessoa[]>(this.apiUrl);
  }

  salvar(pessoa: Pessoa): Observable<Pessoa> {
    if (pessoa.id) {
      return this.http.put<Pessoa>(`${this.apiUrl}/${pessoa.id}`, pessoa);
    } else {
      return this.http.post<Pessoa>(this.apiUrl, pessoa);
    }
  }

  buscarPorId(id: number) {
    return this.http.get(`${this.apiUrl}/${id}`);
  }
  
  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  buscarPorCpf(cpf: string): Observable<Pessoa>{
    return this.http.get<Pessoa>(`${this.apiUrl}/cpf/${cpf}`);
  }
}
