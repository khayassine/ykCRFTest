import { ISousRegion } from 'app/shared/model//sous-region.model';

export interface IRegion {
    id?: number;
    titre?: string;
    villeId?: number;
    sousRegions?: ISousRegion[];
}

export class Region implements IRegion {
    constructor(public id?: number, public titre?: string, public villeId?: number, public sousRegions?: ISousRegion[]) {}
}
