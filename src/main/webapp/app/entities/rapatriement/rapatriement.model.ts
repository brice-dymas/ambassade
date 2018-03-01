import { BaseEntity } from './../../shared';

export const enum Sexe {
    'MASCULIN',
    'FEMININ'
}

export class Rapatriement implements BaseEntity {
    constructor(
        public id?: number,
        public reference?: number,
        public numeroDossier?: string,
        public nom?: string,
        public prenom?: string,
        public dateNaissance?: any,
        public documentID?: string,
        public sexe?: Sexe,
        public motif?: string,
        public dateRapatriement?: any,
        public frontiere?: string,
        public documentScanneContentType?: string,
        public documentScanne?: any,
        public createdByPHPRunner?: number,
    ) {
    }
}
