import { BaseEntity } from './../../shared';

export class CaisseSearchModel implements BaseEntity {
    constructor(
        public id?: number,
        public reference?: number,
        public dateDuJour?: string,
        public dateDuJourFin?: string,
        public nom?: string,
        public prenom?: string,
        public typeID?: string,
        public numeroID?: string,
        public serviceConcerne?: string,
        public monnaie?: string,
        public montant?: number,
        public dateRetour?: string,
        public dateRetourFin?: string,
        public telephone?: string,
        public num?: number,
        public paiement?: string,
    ) {
    }
}
