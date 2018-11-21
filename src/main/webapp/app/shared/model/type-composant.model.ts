export interface ITypeComposant {
    id?: number;
    code?: string;
    titre?: string;
    cssStyle?: string;
}

export class TypeComposant implements ITypeComposant {
    constructor(public id?: number, public code?: string, public titre?: string, public cssStyle?: string) {}
}
