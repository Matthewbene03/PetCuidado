import { Pessoa } from "./pessoa";

export class Funcionario {
    id?: number;
    cargo!: string;
    usuario!: string;
    senha!: string;
    pessoa!: Pessoa;
}