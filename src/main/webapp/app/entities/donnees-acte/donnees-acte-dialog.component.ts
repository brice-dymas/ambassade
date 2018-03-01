import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { DonneesActe } from './donnees-acte.model';
import { DonneesActePopupService } from './donnees-acte-popup.service';
import { DonneesActeService } from './donnees-acte.service';

@Component({
    selector: 'jhi-donnees-acte-dialog',
    templateUrl: './donnees-acte-dialog.component.html'
})
export class DonneesActeDialogComponent implements OnInit {

    donneesActe: DonneesActe;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private donneesActeService: DonneesActeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.donneesActe.id !== undefined) {
            this.subscribeToSaveResponse(
                this.donneesActeService.update(this.donneesActe));
        } else {
            this.subscribeToSaveResponse(
                this.donneesActeService.create(this.donneesActe));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<DonneesActe>>) {
        result.subscribe((res: HttpResponse<DonneesActe>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: DonneesActe) {
        this.eventManager.broadcast({ name: 'donneesActeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-donnees-acte-popup',
    template: ''
})
export class DonneesActePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private donneesActePopupService: DonneesActePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.donneesActePopupService
                    .open(DonneesActeDialogComponent as Component, params['id']);
            } else {
                this.donneesActePopupService
                    .open(DonneesActeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
