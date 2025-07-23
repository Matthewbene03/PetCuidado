import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pet } from '../models/pet';
import { appSettings } from '../app.config';

@Injectable({
  providedIn: 'root'
})

export class PetService {

  private apiUrl = `${appSettings.apiBaseUrl}/pet`;

  constructor(private http: HttpClient) { }

  listar(): Observable<Pet[]> {
    return this.http.get<Pet[]>(this.apiUrl);
  }

  salvar(pet: Pet): Observable<Pet> {
    if (pet.id) {
      return this.http.put<Pet>(`${this.apiUrl}/${pet.id}`, pet);
    } else {
      return this.http.post<Pet>(this.apiUrl, pet);
    }
  }

  buscarPorId(id: number) {
    return this.http.get(`${this.apiUrl}/${id}`);
  }
  
  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  } 
}

