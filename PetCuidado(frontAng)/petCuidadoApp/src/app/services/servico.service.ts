import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pet } from '../models/pet';
import { appSettings } from '../app.config';
import { Servico } from '../models/servico';

@Injectable({
  providedIn: 'root'
})

export class ServicoService {

  private apiUrl = `${appSettings.apiBaseUrl}/servicos`;

  constructor(private http: HttpClient) { }

  listar(): Observable<Servico[]> {
    return this.http.get<Servico[]>(this.apiUrl);
  }

  salvar(servico: Servico): Observable<Servico> {
    if (servico.id) {
      return this.http.put<Servico>(`${this.apiUrl}/${servico.id}`, servico);
    } else {
      return this.http.post<Servico>(this.apiUrl, servico);
    }
  }

  buscarPorId(id: number) {
    return this.http.get(`${this.apiUrl}/${id}`);
  }
  
  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  } 
}

