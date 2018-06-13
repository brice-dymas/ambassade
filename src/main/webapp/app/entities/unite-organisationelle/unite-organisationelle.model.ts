import { BaseEntity } from './../../shared';

export class UniteOrganisationelle implements BaseEntity {
    constructor(
        public id?: number,
        public libelle?: string,
    ) {
    }
}
