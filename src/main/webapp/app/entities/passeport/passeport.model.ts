import { BaseEntity, User } from './../../shared';

export const enum Statut {
    'CELIBATAIRE',
    'MARIE',
    'DIVORCE',
    'SEPARE',
    'VEUF'
}

export const enum State {
    'NOUVEAU',
    'PAYE',
    'ENCOURS',
    'PRET',
    'RETIRER'
}

export const enum Sexe {
    'FEMININ',
    ' MASCULIN'
}

export class Passeport implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public prenom?: string,
        public numeroPasseport?: string,
        public neLe?: any,
        public lieuNaissance?: string,
        public etatCivil?: Statut,
        public adresse?: string,
        public telephone?: string,
        public nif?: string,
        public paysEmetteur?: string,
        public soumisLe?: any,
        public delivreLe?: any,
        public remarques?: string,
        public dateEmission?: any,
        public dateExpiration?: any,
        public sms?: string,
        public taille?: number,
        public recu?: string,
        public photoContentType?: string,
        public photo?: any,
        public state?: State,
        public cin?: string,
        public type?: string,
        public sexe?: Sexe,
        public dateCreation?: any,
        public dateModification?: any,
        public typeService?: BaseEntity,
        public createdBy?: User,
        public modifiedBy?: User,
    ) {
    }
}
