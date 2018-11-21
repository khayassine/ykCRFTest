export interface IRegleValidation {
    id?: number;
    code?: string;
    titre?: string;
    regexValidation?: string;
    signeComparaison?: string;
    message?: string;
}

export class RegleValidation implements IRegleValidation {
    constructor(
        public id?: number,
        public code?: string,
        public titre?: string,
        public regexValidation?: string,
        public signeComparaison?: string,
        public message?: string
    ) {}
}
