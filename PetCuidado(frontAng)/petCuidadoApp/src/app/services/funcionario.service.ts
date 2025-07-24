import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Funcionario } from '../models/funcionario';
import { appSettings } from '../app.config';

@Injectable({
  providedIn: 'root'
})
export class FuncionarioService {

  private apiUrl = `${appSettings.apiBaseUrl}/funcionario`;

  constructor(private http: HttpClient) { }

  listar(): Observable<Funcionario[]> {
    return this.http.get<Funcionario[]>(this.apiUrl);
  }

  salvar(funcionario: Funcionario): Observable<Funcionario> {
    if (funcionario.id) {
      return this.http.put<Funcionario>(`${this.apiUrl}/${funcionario.id}`, funcionario);
    } else {
      return this.http.post<Funcionario>(this.apiUrl, funcionario);
    }
  }

  buscarPorId(id: number) {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  autenticar(usuario: string, senha: string): Observable<Funcionario> {
    return this.http.post<Funcionario>(`${this.apiUrl}/autenticar`, {
      usuario, senha
    });
  }

  getCargoUsuarioLogado(): string | null {
    const usuarioLogado = localStorage.getItem('usuarioLogado');
    if (usuarioLogado) {
      const funcionario = JSON.parse(usuarioLogado);
      return funcionario.cargo;
    }
    return null;
  }

    getIdUsuarioLogado(): number | null {
    const usuarioLogado = localStorage.getItem('usuarioLogado');
    if (usuarioLogado) {
      const funcionario = JSON.parse(usuarioLogado);
      return funcionario.id;
    }
    return null;
  }
}
