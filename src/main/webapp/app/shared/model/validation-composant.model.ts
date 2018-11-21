export const enum NiveauValidation {
    WARNING = 'WARNING',
    INFO = 'INFO',
    ERROR = 'ERROR'
}

export interface IValidationComposant {
    id?: number;
    code?: string;
    titre?: string;
    regexValidation?: string;
    signeComparaison?: string;
    valeurComparaison?: string;
    message?: string;
    niveauValidation?: NiveauValidation;
    composantTemplateId?: number;
    regleValidationId?: number;
}

export class ValidationComposant implements IValidationComposant {
    constructor(
        public id?: number,
        public code?: string,
        public titre?: string,
        public regexValidation?: string,
        public signeComparaison?: string,
        public valeurComparaison?: string,
        public message?: string,
        public niveauValidation?: NiveauValidation,
        public composantTemplateId?: number,
        public regleValidationId?: number
    ) {}
}
