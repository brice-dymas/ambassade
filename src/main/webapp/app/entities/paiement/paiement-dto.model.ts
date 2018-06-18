import { BaseEntity, User } from './../../shared';

export class PaiementDtoModel implements BaseEntity {
    constructor(
        public id?: number,
        public datePaiement?: string,
        public datePaiementFin?: string,
        public numeroPaiement?: string,
        public visa?: number,
        public passeport?: number,
        public typeService?: number,
        public user?: number,
    ) {
    }
}
