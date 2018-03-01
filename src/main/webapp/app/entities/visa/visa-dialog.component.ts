import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Visa } from './visa.model';
import { VisaPopupService } from './visa-popup.service';
import { VisaService } from './visa.service';

@Component({
    selector: 'jhi-visa-dialog',
    templateUrl: './visa-dialog.component.html'
})
export class VisaDialogComponent implements OnInit {

    visa: Visa;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private visaService: VisaService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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
