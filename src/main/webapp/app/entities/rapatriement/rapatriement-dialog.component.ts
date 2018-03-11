import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Rapatriement } from './rapatriement.model';
import { RapatriementPopupService } from './rapatriement-popup.service';
import { RapatriementService } from './rapatriement.service';

@Component({
    selector: 'jhi-rapatriement-dialog',
    templateUrl: './rapatriement-dialog.component.html'
})
export class RapatriementDialogComponent implements OnInit {

    rapatriement: Rapatriement;
    isSaving: boolean;
    dateNaissanceDp: any;
    dateRapatriementDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private rapatriementService: RapatriementService,
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
        if (this.rapatriement.id !== undefined) {
            this.subscribeToSaveResponse(
                this.rapatriementService.update(this.rapatriement));
        } else {
            this.subscribeToSaveResponse(
                this.rapatriementService.create(this.rapatriement));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Rapatriement>>) {
        result.subscribe((res: HttpResponse<Rapatriement>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Rapatriement) {
        this.eventManager.broadcast({ name: 'rapatriementListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-rapatriement-popup',
    template: ''
})
export class RapatriementPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private rapatriementPopupService: RapatriementPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.rapatriementPopupService
                    .open(RapatriementDialogComponent as Component, params['id']);
            } else {
                this.rapatriementPopupService
                    .open(RapatriementDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
