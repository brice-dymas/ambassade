import { BaseEntity } from './../../shared';

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

export class Passeport implements BaseEntity {
    constructor(
        public id?: number,
        public numeroFormulaire?: number,
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
        public montant?: number,
        public remarques?: string,
        public dateEmission?: any,
        public dateExpiration?: any,
        public remarquesR?: string,
        public sms?: string,
        public sms2?: string,
        public documents?: string,
        public taille?: number,
        public recu?: string,
        public photoContentType?: string,
        public photo?: any,
        public state?: State,
        public typeService?: BaseEntity,
    ) {
    }
}
