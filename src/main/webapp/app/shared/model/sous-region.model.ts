export interface ISousRegion {
    id?: number;
    titre?: string;
    regionId?: number;
}

export class SousRegion implements ISousRegion {
    constructor(public id?: number, public titre?: string, public regionId?: number) {}
}
