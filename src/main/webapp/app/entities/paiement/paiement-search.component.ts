import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import {PaiementSearchModel} from './paiement-search.model';
import { TypeService, TypeServiceService } from '../type-service';
import {UniteOrganisationelle} from '../unite-organisationelle/unite-organisationelle.model';
import {UniteOrganisationelleService} from '../unite-organisationelle/unite-organisationelle.service';

@Component({
    selector: 'jhi-paiement-search',
    templateUrl: './paiement-search.component.html',
    styles: []
})
export class PaiementSearchComponent implements OnInit {

    formModel: PaiementSearchModel;

    typeservices: TypeService[];
    uniteOrganisationelles: UniteOrganisationelle[];
    constructor(
        private jhiAlertService: JhiAlertService,
        private typeServiceService: TypeServiceService,
        private uniteOrganisationelleService: UniteOrganisationelleService,
        private eventManager: JhiEventManager
    ) {

    }

    ngOnInit() {
        this.formModel = new PaiementSearchModel();
        this.typeServiceService.query()
            .subscribe((res: HttpResponse<TypeService[]>) => { this.typeservices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.uniteOrganisationelleService.query()
            .subscribe((res: HttpResponse<UniteOrganisationelle[]>) => { this.uniteOrganisationelles = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    search() {
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

    trackUniteOrganisationelleById(index: number, item: UniteOrganisationelle) {
        return item.id;
    }
}
