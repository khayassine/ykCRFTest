export interface IEtude {
    id?: number;
    code?: string;
    titre?: string;
    description?: string;
    pageHtml?: string;
    cssGlobal?: string;
}

export class Etude implements IEtude {
    constructor(
        public id?: number,
        public code?: string,
        public titre?: string,
        public description?: string,
        public pageHtml?: string,
        public cssGlobal?: string
    ) {}
}
