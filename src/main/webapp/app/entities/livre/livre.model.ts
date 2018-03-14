import { BaseEntity } from './../../shared';

export class Livre implements BaseEntity {
    constructor(
        public id?: number,
        public codeISBN?: string,
        public auteur?: string,
        public titre?: string,
        public edition?: string,
        public etagere?: string,
        public annee?: number,
        public categorie?: string,
        public resume?: string,
        public quantite?: number,
        public disponible?: string,
        public page?: string,
        public consultation?: string,
        public origine?: string,
        public sousTitre?: string,
        public collection?: string,
        public impression?: string,
        public format?: string,
        public index?: string,
        public bibliographie?: string,
        public lieuEdition?: string,
        public lieuImpression?: string,
        public illustration?: string,
        public observation?: string,
        public prenom?: string,
        public statistique?: string,
        public glossaire?: string,
    ) {
    }
}
