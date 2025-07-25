import { Pet } from "./pet";
import { Servico } from "./servico";
import { Funcionario } from "./funcionario";

export class Agendamento {
    id?: number;
    status!: string;
    data!: Date;
    pet!: Pet;
    servico!: Servico;
    funcionario!: Funcionario;
}

