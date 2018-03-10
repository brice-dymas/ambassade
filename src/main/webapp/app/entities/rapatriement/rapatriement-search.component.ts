import { Component, OnInit } from '@angular/core';

import { JhiEventManager } from 'ng-jhipster';

import {RapatriementDtoModel} from './rapatriement-dto.model';

@Component({
    selector: 'jhi-rapatriement-search',
    templateUrl: './rapatriement-search.component.html',
    styles: []
})
export class RapatriementSearchComponent implements OnInit {

    formModel: RapatriementDtoModel;
    constructor(private eventManager: JhiEventManager) { }

    ngOnInit() {
        this.formModel = new RapatriementDtoModel();
    }

    search() {
        this.eventManager.broadcast({ name: 'rapatriementListModification', content: this.formModel});
    }

}
