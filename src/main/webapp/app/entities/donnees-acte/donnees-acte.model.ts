import { BaseEntity } from './../../shared';

export const enum Sexe {
    'MASCULIN',
    'FEMININ'
}

export const enum Statut {
    'CELIBATAIRE',
    'MARIE',
    'DIVORCE',
    'SEPARE',
    'VEUF'
}

export class DonneesActe implements BaseEntity {
    constructor(
        public id?: number,
        public reference?: string,
        public dateDuJourChiffre?: any,
        public registreSpecialRD?: string,
        public nomEnfant?: string,
        public registre?: string,
        public annee?: number,
        public numero?: string,
        public dateNaissanceChiffre?: any,
        public nomPere?: string,
        public prenomPere?: string,
        public nomMere?: string,
        public prenomMere?: string,
        public sexe?: Sexe,
        public statut?: Statut,
        public villeNaissance?: string,
        public adressePere?: string,
        public adresseMere?: string,
        public temoins1?: string,
        public temoins2?: string,
        public idPere?: string,
        public idMere?: string,
        public photoContentType?: string,
        public photo?: any,
        public juridiction?: string,
        public livre?: string,
        public notes?: string,
        public feuille?: string,
        public acte?: string,
    ) {
    }
}
