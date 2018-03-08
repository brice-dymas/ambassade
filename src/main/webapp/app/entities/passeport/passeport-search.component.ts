import { Component, OnInit } from '@angular/core';

import { JhiEventManager } from 'ng-jhipster';

import { Passeport } from './passeport.model';

@Component({
    selector: 'jhi-passeport-search',
    templateUrl: './passeport-search.component.html',
    styles: []
})
export class PasseportSearchComponent implements OnInit {

    formModel: Passeport;

    constructor(private eventManager: JhiEventManager) { }

    ngOnInit() {
        this.formModel = new Passeport();
    }

    search() {
        this.eventManager.broadcast({ name: 'passeportListModification', content: this.formModel});
    }

}
