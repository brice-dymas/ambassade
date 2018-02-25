import { BaseEntity } from './../../shared';

export class Categorie implements BaseEntity {
    constructor(
        public id?: number,
        public nomCategorie?: string,
    ) {
    }
}
