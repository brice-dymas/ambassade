import { BaseEntity } from './../../shared';

export class TypeEntree implements BaseEntity {
    constructor(
        public id?: number,
        public libelle?: string,
    ) {
    }
}
