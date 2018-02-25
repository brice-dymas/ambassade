import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Monnaie } from './monnaie.model';
import { MonnaiePopupService } from './monnaie-popup.service';
import { MonnaieService } from './monnaie.service';

@Component({
    selector: 'jhi-monnaie-dialog',
    templateUrl: './monnaie-dialog.component.html'
})
export class MonnaieDialogComponent implements OnInit {

    monnaie: Monnaie;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private monnaieService: MonnaieService,
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
        if (this.monnaie.id !== undefined) {
            this.subscribeToSaveResponse(
                this.monnaieService.update(this.monnaie));
        } else {
            this.subscribeToSaveResponse(
                this.monnaieService.create(this.monnaie));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Monnaie>>) {
        result.subscribe((res: HttpResponse<Monnaie>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Monnaie) {
        this.eventManager.broadcast({ name: 'monnaieListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-monnaie-popup',
    template: ''
})
export class MonnaiePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private monnaiePopupService: MonnaiePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.monnaiePopupService
                    .open(MonnaieDialogComponent as Component, params['id']);
            } else {
                this.monnaiePopupService
                    .open(MonnaieDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
