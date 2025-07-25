import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Funcionario } from '../models/funcionario';
import { appSettings } from '../app.config';
import { Agendamento } from '../models/agendamento';

@Injectable({
  providedIn: 'root'
})

export class AgendamentoService {

  private apiUrl = `${appSettings.apiBaseUrl}/agendamentos`;

  constructor(private http: HttpClient) { }

  listar(): Observable<Agendamento[]> {
    return this.http.get<Agendamento[]>(this.apiUrl);
  }

  salvar(agendamento: Agendamento): Observable<Agendamento> {
    if (agendamento.id) {
      return this.http.put<Agendamento>(`${this.apiUrl}/${agendamento.id}`, agendamento);
    } else {
      agendamento.status = 'NAO_CONCLUIDO';
      return this.http.post<Agendamento>(this.apiUrl, agendamento);
    }
  }

  buscarPorId(id: number) {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
