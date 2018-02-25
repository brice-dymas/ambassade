import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Produit } from './produit.model';
import { ProduitPopupService } from './produit-popup.service';
import { ProduitService } from './produit.service';

@Component({
    selector: 'jhi-produit-dialog',
    templateUrl: './produit-dialog.component.html'
})
export class ProduitDialogComponent implements OnInit {

    produit: Produit;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private produitService: ProduitService,
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
        if (this.produit.id !== undefined) {
            this.subscribeToSaveResponse(
                this.produitService.update(this.produit));
        } else {
            this.subscribeToSaveResponse(
                this.produitService.create(this.produit));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Produit>>) {
        result.subscribe((res: HttpResponse<Produit>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Produit) {
        this.eventManager.broadcast({ name: 'produitListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-produit-popup',
    template: ''
})
export class ProduitPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private produitPopupService: ProduitPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.produitPopupService
                    .open(ProduitDialogComponent as Component, params['id']);
            } else {
                this.produitPopupService
                    .open(ProduitDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
