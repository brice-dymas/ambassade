import { BaseEntity } from './../../shared';

export class PaiementSearchModel {
    constructor(
        public datePaiement?: string,
        public datePaiementFin?: string,
        public numeroPaiement?: string,
        public typeService?: number,
        public uniteOrganisationelle?: number,
    ) {
    }
}
