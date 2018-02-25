import { BaseEntity } from './../../shared';

export class Montant implements BaseEntity {
    constructor(
        public id?: number,
        public monnaie?: string,
        public montant?: number,
        public produit?: string,
    ) {
    }
}
