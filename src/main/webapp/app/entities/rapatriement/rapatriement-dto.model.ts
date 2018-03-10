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
        public dateNaissance?: any,
        public dateNaissanceFin?: any,
        public documentID?: string,
        public sexe?: Sexes,
        public motif?: string,
        public dateRapatriement?: any,
        public dateRapatriementFin?: any,
        public frontiere?: string,
        public documentScanneContentType?: string,
        public documentScanne?: any,
        public createdByPHPRunner?: number,
    ) {
    }
}
