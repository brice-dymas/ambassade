import { Component, OnInit } from '@angular/core';

import { JhiEventManager } from 'ng-jhipster';

import { DonneesActeSearchModel } from './donnees-acte-search.model';

@Component({
    selector: 'jhi-donnees-acte-search',
    templateUrl: './donnees-acte-search.component.html'
})
export class DonneesActeSearchComponent implements OnInit {

    formModel: DonneesActeSearchModel;

    constructor( private eventManager: JhiEventManager ) {}

    ngOnInit() {
        this.formModel = new DonneesActeSearchModel();
    }

    search() {
        this.eventManager.broadcast({ name: 'donneesActeListModification', content: this.formModel});
    }
}
