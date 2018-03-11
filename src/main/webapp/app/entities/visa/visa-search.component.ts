import { Component, OnInit } from '@angular/core';

import { JhiEventManager } from 'ng-jhipster';

// import { Visa } from './Visa.model';
import { VisaDtoModel} from './visa-dto.model';

@Component({
    selector: 'jhi-visa-search',
    templateUrl: './visa-search.component.html',
    styles: []
})
export class VisaSearchComponent implements OnInit {

    // formModel: Visa;
    formModel: VisaDtoModel;
    constructor(private eventManager: JhiEventManager) { }

    ngOnInit() {
        // this.formModel = new Visa();
        this.formModel = new VisaDtoModel();
    }

    search() {
        this.eventManager.broadcast({ name: 'visaListModification', content: this.formModel});
    }

}
