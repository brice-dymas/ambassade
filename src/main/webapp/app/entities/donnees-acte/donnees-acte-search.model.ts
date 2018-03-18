import { BaseEntity } from './../../shared';

export const enum Sexes {
    'MASCULIN',
    'FEMININ'
}

export const enum Statuts {
    'CELIBATAIRE',
    'MARIE',
    'DIVORCE',
    'SEPARE',
    'VEUF'
}

export class DonneesActeSearchModel implements BaseEntity {
    constructor(
        public id?: number,
        public reference?: string,
        public dateDuJourChiffre?: string,
        public dateDuJourChiffreFin?: string,
        public registreSpecialRD?: string,
        public nomEnfant?: string,
        public registre?: string,
        public annee?: number,
        public numero?: string,
        public dateNaissanceChiffre?: string,
        public dateNaissanceChiffreFin?: string,
        public nomPere?: string,
        public prenomPere?: string,
        public nomMere?: string,
        public prenomMere?: string,
        public sexe?: Sexes,
        public statut?: Statuts,
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
