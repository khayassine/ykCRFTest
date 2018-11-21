export interface ICommentaireRequette {
    id?: number;
    commentaire?: string;
    requetteId?: number;
}

export class CommentaireRequette implements ICommentaireRequette {
    constructor(public id?: number, public commentaire?: string, public requetteId?: number) {}
}
