import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TypeService } from './type-service.model';
import { TypeServicePopupService } from './type-service-popup.service';
import { TypeServiceService } from './type-service.service';
import { UniteOrganisationelle, UniteOrganisationelleService } from '../unite-organisationelle';

@Component({
    selector: 'jhi-type-service-dialog',
    templateUrl: './type-service-dialog.component.html'
})
export class TypeServiceDialogComponent implements OnInit {

    typeService: TypeService;
    isSaving: boolean;

    uniteorganisationelles: UniteOrganisationelle[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private typeServiceService: TypeServiceService,
        private uniteOrganisationelleService: UniteOrganisationelleService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.uniteOrganisationelleService.query()
            .subscribe((res: HttpResponse<UniteOrganisationelle[]>) => { this.uniteorganisationelles = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.typeService.id !== undefined) {
            this.subscribeToSaveResponse(
                this.typeServiceService.update(this.typeService));
        } else {
            this.subscribeToSaveResponse(
                this.typeServiceService.create(this.typeService));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TypeService>>) {
        result.subscribe((res: HttpResponse<TypeService>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TypeService) {
        this.eventManager.broadcast({ name: 'typeServiceListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUniteOrganisationelleById(index: number, item: UniteOrganisationelle) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-type-service-popup',
    template: ''
})
export class TypeServicePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private typeServicePopupService: TypeServicePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.typeServicePopupService
                    .open(TypeServiceDialogComponent as Component, params['id']);
            } else {
                this.typeServicePopupService
                    .open(TypeServiceDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
