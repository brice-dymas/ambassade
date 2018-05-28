import { BaseEntity } from './../../shared';

export const enum Statuts {
    'CELIBATAIRE',
    'MARIE',
    'DIVORCE',
    'SEPARE',
    'VEUF'
}

export class PasseportModelDTO implements BaseEntity {
    constructor(
        public id?: number,
        public numeroFormulaire?: number,
        public nom?: string,
        public prenom?: string,
        public numeroPasseport?: string,
        public neLe?: string,
        public neLeFin?: string,
        public lieuNaissance?: string,
        public etatCivil?: Statuts,
        public adresse?: string,
        public telephone?: string,
        public nif?: string,
        public paysEmetteur?: string,
        public soumisLe?: any,
        public soumisLeFin?: any,
        public delivreLe?: any,
        public delivreLeFin?: any,
        public montant?: number,
        public remarques?: string,
        public dateEmission?: any,
        public dateEmissionFin?: any,
        public dateExpiration?: string,
        public dateExpirationFin?: string,
        public remarquesR?: string,
        public sms?: string,
        public sms2?: string,
        public documents?: string,
        public recu?: string,
    ) {
    }
}
