import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { Visa } from './visa.model';
import { VisaPopupService } from './visa-popup.service';
import { VisaService } from './visa.service';
import { TypeService, TypeServiceService } from '../type-service';
import { TypeEntree, TypeEntreeService } from '../type-entree';
import { Categorie, CategorieService } from '../categorie';

@Component({
    selector: 'jhi-visa-dialog',
    templateUrl: './visa-dialog.component.html'
})
export class VisaDialogComponent implements OnInit {

    visa: Visa;
    isSaving: boolean;

    typeservices: TypeService[];

    typeentrees: TypeEntree[];

    categories: Categorie[];
    dateEmissionDp: any;
    dateExpirationDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private visaService: VisaService,
        private typeServiceService: TypeServiceService,
        private typeEntreeService: TypeEntreeService,
        private categorieService: CategorieService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.typeServiceService.query()
            .subscribe((res: HttpResponse<TypeService[]>) => { this.typeservices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.typeEntreeService.query()
            .subscribe((res: HttpResponse<TypeEntree[]>) => { this.typeentrees = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.categorieService.query()
            .subscribe((res: HttpResponse<Categorie[]>) => { this.categories = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.visa, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.visa.id !== undefined) {
            this.subscribeToSaveResponse(
                this.visaService.update(this.visa));
        } else {
            this.subscribeToSaveResponse(
                this.visaService.create(this.visa));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Visa>>) {
        result.subscribe((res: HttpResponse<Visa>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Visa) {
        this.eventManager.broadcast({ name: 'visaListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTypeServiceById(index: number, item: TypeService) {
        return item.id;
    }

    trackTypeEntreeById(index: number, item: TypeEntree) {
        return item.id;
    }

    trackCategorieById(index: number, item: Categorie) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-visa-popup',
    template: ''
})
export class VisaPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private visaPopupService: VisaPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.visaPopupService
                    .open(VisaDialogComponent as Component, params['id']);
            } else {
                this.visaPopupService
                    .open(VisaDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
