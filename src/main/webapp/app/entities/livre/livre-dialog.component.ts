import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Livre } from './livre.model';
import { LivrePopupService } from './livre-popup.service';
import { LivreService } from './livre.service';

@Component({
    selector: 'jhi-livre-dialog',
    templateUrl: './livre-dialog.component.html'
})
export class LivreDialogComponent implements OnInit {

    livre: Livre;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private livreService: LivreService,
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
        if (this.livre.id !== undefined) {
            this.subscribeToSaveResponse(
                this.livreService.update(this.livre));
        } else {
            this.subscribeToSaveResponse(
                this.livreService.create(this.livre));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Livre>>) {
        result.subscribe((res: HttpResponse<Livre>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Livre) {
        this.eventManager.broadcast({ name: 'livreListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-livre-popup',
    template: ''
})
export class LivrePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private livrePopupService: LivrePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.livrePopupService
                    .open(LivreDialogComponent as Component, params['id']);
            } else {
                this.livrePopupService
                    .open(LivreDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
