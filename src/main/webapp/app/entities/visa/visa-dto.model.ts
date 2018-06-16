import { BaseEntity } from './../../shared';

export class VisaDtoModel {
    constructor(
        public nom?: string,
        public prenom?: string,
        public numeroPasseport?: string,
        public numeroVisa?: number,
        public dateEmission?: string,
        public dateEmissionFin?: string,
        public dateExpiration?: string,
        public dateExpirationFin?: string,
        public typeService?: number,
        public categorie?: number,
    ) {
    }
}
