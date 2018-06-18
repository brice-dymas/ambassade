import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TypeEntree } from './type-entree.model';
import { TypeEntreePopupService } from './type-entree-popup.service';
import { TypeEntreeService } from './type-entree.service';

@Component({
    selector: 'jhi-type-entree-dialog',
    templateUrl: './type-entree-dialog.component.html'
})
export class TypeEntreeDialogComponent implements OnInit {

    typeEntree: TypeEntree;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private typeEntreeService: TypeEntreeService,
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
        if (this.typeEntree.id !== undefined) {
            this.subscribeToSaveResponse(
                this.typeEntreeService.update(this.typeEntree));
        } else {
            this.subscribeToSaveResponse(
                this.typeEntreeService.create(this.typeEntree));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TypeEntree>>) {
        result.subscribe((res: HttpResponse<TypeEntree>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TypeEntree) {
        this.eventManager.broadcast({ name: 'typeEntreeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-type-entree-popup',
    template: ''
})
export class TypeEntreePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private typeEntreePopupService: TypeEntreePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.typeEntreePopupService
                    .open(TypeEntreeDialogComponent as Component, params['id']);
            } else {
                this.typeEntreePopupService
                    .open(TypeEntreeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
