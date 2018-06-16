import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UniteOrganisationelle } from './unite-organisationelle.model';
import { UniteOrganisationellePopupService } from './unite-organisationelle-popup.service';
import { UniteOrganisationelleService } from './unite-organisationelle.service';

@Component({
    selector: 'jhi-unite-organisationelle-dialog',
    templateUrl: './unite-organisationelle-dialog.component.html'
})
export class UniteOrganisationelleDialogComponent implements OnInit {

    uniteOrganisationelle: UniteOrganisationelle;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private uniteOrganisationelleService: UniteOrganisationelleService,
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
        if (this.uniteOrganisationelle.id !== undefined) {
            this.subscribeToSaveResponse(
                this.uniteOrganisationelleService.update(this.uniteOrganisationelle));
        } else {
            this.subscribeToSaveResponse(
                this.uniteOrganisationelleService.create(this.uniteOrganisationelle));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UniteOrganisationelle>>) {
        result.subscribe((res: HttpResponse<UniteOrganisationelle>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UniteOrganisationelle) {
        this.eventManager.broadcast({ name: 'uniteOrganisationelleListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-unite-organisationelle-popup',
    template: ''
})
export class UniteOrganisationellePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private uniteOrganisationellePopupService: UniteOrganisationellePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.uniteOrganisationellePopupService
                    .open(UniteOrganisationelleDialogComponent as Component, params['id']);
            } else {
                this.uniteOrganisationellePopupService
                    .open(UniteOrganisationelleDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
