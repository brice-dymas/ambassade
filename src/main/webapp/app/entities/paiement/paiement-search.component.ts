import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Paiement } from './paiement.model';
import {PaiementSearchModel} from './paiement-search.model';
import { TypeService, TypeServiceService } from '../type-service';

@Component({
    selector: 'jhi-paiement-search',
    templateUrl: './paiement-search.component.html',
    styles: []
})
export class PaiementSearchComponent implements OnInit {

    formModel: PaiementSearchModel;

    typeservices: TypeService[];
    // formModel: PaiementModelDTO;
    constructor(
        private jhiAlertService: JhiAlertService,
        private typeServiceService: TypeServiceService,
        private eventManager: JhiEventManager
    ) {

    }

    ngOnInit() {
        this.formModel = new PaiementSearchModel();
        this.typeServiceService.query()
            .subscribe((res: HttpResponse<TypeService[]>) => { this.typeservices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        // this.formModel = new PaiementModelDTO();
    }

    search() {
        console.log(this.formModel);
        this.eventManager.broadcast({ name: 'paiementListModification', content: this.formModel});
    }
    resetSearch() {
        this.formModel = new PaiementSearchModel();
        this.eventManager.broadcast({ name: 'paiementListModification', content: this.formModel});
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTypeServiceById(index: number, item: TypeService) {
        return item.id;
    }
}
