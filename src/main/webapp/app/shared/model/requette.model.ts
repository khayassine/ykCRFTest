import { ICommentaireRequette } from 'app/shared/model//commentaire-requette.model';

export const enum EtatRequette {
    COMMENTED = 'COMMENTED',
    RESOLVED = 'RESOLVED'
}

export interface IRequette {
    id?: number;
    etat?: EtatRequette;
    composantValeurId?: number;
    validationComposantId?: number;
    commentaireRequettes?: ICommentaireRequette[];
}

export class Requette implements IRequette {
    constructor(
        public id?: number,
        public etat?: EtatRequette,
        public composantValeurId?: number,
        public validationComposantId?: number,
        public commentaireRequettes?: ICommentaireRequette[]
    ) {}
}
