import { BaseEntity } from './../../shared';

export class Passeport implements BaseEntity {
    constructor(
        public id?: number,
        public numeroFormulaire?: number,
        public nom?: string,
        public prenom?: string,
        public numeroPasseport?: string,
        public neLe?: string,
        public lieuNaissance?: string,
        public etatCivil?: string,
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
    ) {
    }
}
