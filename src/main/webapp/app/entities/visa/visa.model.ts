import { BaseEntity } from './../../shared';

export class Visa implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public prenom?: string,
        public nationalite?: string,
        public numeroPasseport?: string,
        public cedula?: string,
        public numeroVisa?: number,
        public dateEmission?: any,
        public dateExpiration?: any,
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
