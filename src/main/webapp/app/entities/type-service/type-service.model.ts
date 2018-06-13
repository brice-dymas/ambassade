import { BaseEntity } from './../../shared';

export class TypeService implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public montant?: number,
        public deleted?: boolean,
        public uniteOrganisationelle?: BaseEntity,
    ) {
        this.deleted = false;
    }
}
