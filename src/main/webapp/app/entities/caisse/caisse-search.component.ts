import { Component, OnInit } from '@angular/core';
import { JhiEventManager } from 'ng-jhipster';

import {CaisseSearchModel} from './caisse-search.model';

@Component({
    selector: 'jhi-caisse-search',
    templateUrl: './caisse-search.component.html'
})
export class CaisseSearchComponent implements OnInit {

    formModel: CaisseSearchModel;

    constructor(private eventManager: JhiEventManager) { }

    ngOnInit() {
        this.formModel = new CaisseSearchModel();
    }

    search() {
        this.eventManager.broadcast({ name: 'caisseListModification', content: this.formModel});
    }
}
