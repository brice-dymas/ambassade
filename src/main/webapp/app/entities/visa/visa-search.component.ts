import { Component, OnInit } from '@angular/core';

import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { VisaDtoModel} from './visa-dto.model';
import { TypeServiceService, TypeService } from '../type-service';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {Categorie} from '../categorie/categorie.model';
import {CategorieService} from '../categorie/categorie.service';

@Component({
    selector: 'jhi-visa-search',
    templateUrl: './visa-search.component.html',
    styles: []
})
export class VisaSearchComponent implements OnInit {

    // formModel: Visa;
    formModel: VisaDtoModel;
    typeservices: TypeService[];
    categories: Categorie[];

    constructor(private eventManager: JhiEventManager,
        private jhiAlertService: JhiAlertService,
        private categorieService: CategorieService,
        private typeServiceService: TypeServiceService) { }

    ngOnInit() {
        this.formModel = new VisaDtoModel();
        this.typeServiceService.query()
            .subscribe((res: HttpResponse<TypeService[]>) => { this.typeservices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.categorieService.query()
            .subscribe((res: HttpResponse<Categorie[]>) => { this.categories = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    search() {
        // const copy: Object = Object.assign({}, this.formModel.typeService);
        // this.formModel.typeService = copy['id'];
        this.eventManager.broadcast({ name: 'visaListModification', content: this.formModel});
    }

    searchReset() {
        this.formModel = new VisaDtoModel();
        this.eventManager.broadcast({ name: 'visaListModification', content: this.formModel});
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTypeServiceById(index: number, item: TypeService) {
        return item.id;
    }

    trackCategorieById(index: number, item: Categorie) {
        return item.id;
    }

}
