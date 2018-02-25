import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Montant } from './montant.model';
import { MontantPopupService } from './montant-popup.service';
import { MontantService } from './montant.service';

@Component({
    selector: 'jhi-montant-dialog',
    templateUrl: './montant-dialog.component.html'
})
export class MontantDialogComponent implements OnInit {

    montant: Montant;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private montantService: MontantService,
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
        if (this.montant.id !== undefined) {
            this.subscribeToSaveResponse(
                this.montantService.update(this.montant));
        } else {
            this.subscribeToSaveResponse(
                this.montantService.create(this.montant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Montant>>) {
        result.subscribe((res: HttpResponse<Montant>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Montant) {
        this.eventManager.broadcast({ name: 'montantListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-montant-popup',
    template: ''
})
export class MontantPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private montantPopupService: MontantPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.montantPopupService
                    .open(MontantDialogComponent as Component, params['id']);
            } else {
                this.montantPopupService
                    .open(MontantDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
