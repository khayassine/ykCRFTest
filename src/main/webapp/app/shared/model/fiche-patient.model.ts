import { Moment } from 'moment';
import { IFormulairePatient } from 'app/shared/model//formulaire-patient.model';

export interface IFichePatient {
    id?: number;
    codePatient?: string;
    oneDate?: Moment;
    centreId?: number;
    formulairePatients?: IFormulairePatient[];
}

export class FichePatient implements IFichePatient {
    constructor(
        public id?: number,
        public codePatient?: string,
        public oneDate?: Moment,
        public centreId?: number,
        public formulairePatients?: IFormulairePatient[]
    ) {}
}
