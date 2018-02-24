import { BaseEntity } from './../../shared';

export class Produit implements BaseEntity {
    constructor(
        public id?: number,
        public nomProduit?: string,
        public monnaie?: string,
        public montant?: number,
    ) {
    }
}
