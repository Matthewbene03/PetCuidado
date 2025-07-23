import { Pet } from "./pet";
import { Servico } from "./servico";
import { Funcionario } from "./funcionario";

export class Agendamento {
    id?: number;
    data!: Date;
    pet!: Pet;
    servico!: Servico;
    funcionario!: Funcionario;
}

