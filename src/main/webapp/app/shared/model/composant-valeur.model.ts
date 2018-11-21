export const enum EtatValeur {
    ND = 'ND',
    COMPLETED = 'COMPLETED',
    QUERIES = 'QUERIES'
}

export interface IComposantValeur {
    id?: number;
    valeur?: string;
    etat?: EtatValeur;
    formulairePatientId?: number;
    composantTemplateId?: number;
}

export class ComposantValeur implements IComposantValeur {
    constructor(
        public id?: number,
        public valeur?: string,
        public etat?: EtatValeur,
        public formulairePatientId?: number,
        public composantTemplateId?: number
    ) {}
}
