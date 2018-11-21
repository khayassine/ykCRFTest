import { IRegion } from 'app/shared/model//region.model';

export interface IVille {
    id?: number;
    titre?: string;
    regions?: IRegion[];
}

export class Ville implements IVille {
    constructor(public id?: number, public titre?: string, public regions?: IRegion[]) {}
}
