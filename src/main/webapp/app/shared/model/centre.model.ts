export interface ICentre {
    id?: number;
    code?: string;
    titre?: string;
    complement?: string;
    villeId?: number;
    regionId?: number;
    sousRegionId?: number;
}

export class Centre implements ICentre {
    constructor(
        public id?: number,
        public code?: string,
        public titre?: string,
        public complement?: string,
        public villeId?: number,
        public regionId?: number,
        public sousRegionId?: number
    ) {}
}
