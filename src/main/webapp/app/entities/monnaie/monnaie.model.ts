import { BaseEntity } from './../../shared';

export class Monnaie implements BaseEntity {
    constructor(
        public id?: number,
        public type?: string,
        public montant?: number,
        public produit?: string,
    ) {
    }
}
