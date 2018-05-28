import { BaseEntity } from './../../shared';

export class VisaDtoModel implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public prenom?: string,
        public nationalite?: string,
        public numeroPasseport?: string,
        public cedula?: string,
        public numeroVisa?: number,
        public dateEmission?: string,
        public dateEmissionFin?: string,
        public dateExpiration?: string,
        public dateExpirationFin?: string,
        public validePour?: number,
        public nombreEntree?: string,
        public type?: string,
        public categorie?: string,
        public taxes?: number,
        public adresse?: string,
        public remarques?: string,
        public typeService?: number,
    ) {
    }
}
