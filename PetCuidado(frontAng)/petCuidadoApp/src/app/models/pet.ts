import { Pessoa } from "./pessoa";

export class Pet {
    id?: number;
    nome!: String;
    especie!: String;
    raca!: String;
    idade!: number;
    pessoa!: Pessoa;
}