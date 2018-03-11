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
        public dateEmission?: any,
        public dateEmissionFin?: any,
        public dateExpiration?: any,
        public dateExpirationFin?: any,
        public validePour?: number,
        public nombreEntree?: string,
        public type?: string,
        public categorie?: string,
        public taxes?: number,
        public adresse?: string,
        public remarques?: string,
    ) {
    }
}
