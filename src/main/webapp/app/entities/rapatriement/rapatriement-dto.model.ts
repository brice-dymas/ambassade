import { BaseEntity } from './../../shared';

export const enum Sexes {
    'MASCULIN',
    'FEMININ'
}

export class RapatriementDtoModel implements BaseEntity {
    constructor(
        public id?: number,
        public reference?: number,
        public numeroDossier?: string,
        public nom?: string,
        public prenom?: string,
        public dateNaissance?: string,
        public dateNaissanceFin?: string,
        public documentID?: string,
        public sexe?: Sexes,
        public motif?: string,
        public dateRapatriement?: string,
        public dateRapatriementFin?: string,
        public frontiere?: string,
        public documentScanneContentType?: string,
        public documentScanne?: any,
        public createdByPHPRunner?: number,
    ) {
    }
}
