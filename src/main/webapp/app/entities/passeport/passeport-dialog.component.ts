import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Passeport } from './passeport.model';
import { PasseportPopupService } from './passeport-popup.service';
import { PasseportService } from './passeport.service';

@Component({
    selector: 'jhi-passeport-dialog',
    templateUrl: './passeport-dialog.component.html'
})
export class PasseportDialogComponent implements OnInit {

    passeport: Passeport;
    isSaving: boolean;
    neLeDp: any;
    soumisLeDp: any;
    delivreLeDp: any;
    dateEmissionDp: any;
    dateExpirationDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private passeportService: PasseportService,
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
        if (this.passeport.id !== undefined) {
            this.subscribeToSaveResponse(
                this.passeportService.update(this.passeport));
        } else {
            this.subscribeToSaveResponse(
                this.passeportService.create(this.passeport));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Passeport>>) {
        result.subscribe((res: HttpResponse<Passeport>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Passeport) {
        this.eventManager.broadcast({ name: 'passeportListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-passeport-popup',
    template: ''
})
export class PasseportPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private passeportPopupService: PasseportPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.passeportPopupService
                    .open(PasseportDialogComponent as Component, params['id']);
            } else {
                this.passeportPopupService
                    .open(PasseportDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
