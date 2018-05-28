import { BaseEntity } from './../../shared';

export class PaiementSearchModel implements BaseEntity {
    constructor(
        public id?: number,
        public datePaiement?: string,
        public visa?: number,
        public passeport?: number,
        public typeService?: number,
        public user?: number,
    ) {
    }
}
