import { IValidationComposant } from 'app/shared/model//validation-composant.model';
import { IComposantTemplate } from 'app/shared/model//composant-template.model';

export interface IComposantTemplate {
    id?: number;
    code?: string;
    titre?: string;
    ordre?: number;
    conditionAffichage?: string;
    texteDroite?: string;
    cssStyle?: string;
    formulaireTemplateId?: number;
    typeComposantId?: number;
    validationComposants?: IValidationComposant[];
    composantTemplateId?: number;
    sousComposants?: IComposantTemplate[];
}

export class ComposantTemplate implements IComposantTemplate {
    constructor(
        public id?: number,
        public code?: string,
        public titre?: string,
        public ordre?: number,
        public conditionAffichage?: string,
        public texteDroite?: string,
        public cssStyle?: string,
        public formulaireTemplateId?: number,
        public typeComposantId?: number,
        public validationComposants?: IValidationComposant[],
        public composantTemplateId?: number,
        public sousComposants?: IComposantTemplate[]
    ) {}
}
