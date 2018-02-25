import { BaseEntity } from './../../shared';

export class Caisse implements BaseEntity {
    constructor(
        public id?: number,
        public reference?: number,
        public dateDuJour?: any,
        public nom?: string,
        public prenom?: string,
        public typeID?: string,
        public numeroID?: string,
        public serviceConcerne?: string,
        public monnaie?: string,
        public montant?: number,
        public dateRetour?: any,
        public telephone?: string,
        public num?: number,
        public paiement?: string,
    ) {
    }
}
