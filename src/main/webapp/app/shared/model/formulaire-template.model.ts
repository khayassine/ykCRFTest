import { IComposantTemplate } from 'app/shared/model//composant-template.model';

export interface IFormulaireTemplate {
    id?: number;
    code?: string;
    titre?: string;
    version?: string;
    composantTemplates?: IComposantTemplate[];
}

export class FormulaireTemplate implements IFormulaireTemplate {
    constructor(
        public id?: number,
        public code?: string,
        public titre?: string,
        public version?: string,
        public composantTemplates?: IComposantTemplate[]
    ) {}
}
