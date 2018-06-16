import { BaseEntity, User } from './../../shared';

export const enum State {
    'NOUVEAU',
    'PAYE',
    'ENCOURS',
    'PRET',
    'RETIRER'
}

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
        public taxes?: number,
        public adresse?: string,
        public remarques?: string,
        public state?: State,
        public profession?: string,
        public nombreDeJour?: number,
        public photoDemandeurVisaContentType?: string,
        public photoDemandeurVisa?: any,
        public email?: string,
        public adresseDeSejour?: string,
        public nomEmployeur?: string,
        public adresseEmployeur?: string,
        public telephoneEmployeur?: string,
        public emailEmployeur?: string,
        public dateCreation?: any,
        public dateModification?: any,
        public typeService?: BaseEntity,
        public typeEntree?: BaseEntity,
        public categorie?: BaseEntity,
        public createdBy?: User,
        public modifiedBy?: User,
    ) {
    }
}
