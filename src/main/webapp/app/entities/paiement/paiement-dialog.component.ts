import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Paiement } from './paiement.model';
import { PaiementPopupService } from './paiement-popup.service';
import { PaiementService } from './paiement.service';
import { Visa, VisaService } from '../visa';
import { Passeport, PasseportService } from '../passeport';
import { TypeService, TypeServiceService } from '../type-service';
import { User, UserService } from '../../shared';

@Component({
    selector: 'jhi-paiement-dialog',
    templateUrl: './paiement-dialog.component.html'
})
export class PaiementDialogComponent implements OnInit {

    paiement: Paiement;
    isSaving: boolean;

    visas: Visa[];

    passeports: Passeport[];

    typeservices: TypeService[];

    users: User[];
    datePaiementDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private paiementService: PaiementService,
        private visaService: VisaService,
        private passeportService: PasseportService,
        private typeServiceService: TypeServiceService,
        private userService: UserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.visaService.query()
            .subscribe((res: HttpResponse<Visa[]>) => { this.visas = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.passeportService.query()
            .subscribe((res: HttpResponse<Passeport[]>) => { this.passeports = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.typeServiceService.query()
            .subscribe((res: HttpResponse<TypeService[]>) => { this.typeservices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userService.query()
            .subscribe((res: HttpResponse<User[]>) => { this.users = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.paiement.id !== undefined) {
            this.subscribeToSaveResponse(
                this.paiementService.update(this.paiement));
        } else {
            this.subscribeToSaveResponse(
                this.paiementService.create(this.paiement));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Paiement>>) {
        result.subscribe((res: HttpResponse<Paiement>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Paiement) {
        this.eventManager.broadcast({ name: 'paiementListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackVisaById(index: number, item: Visa) {
        return item.id;
    }

    trackPasseportById(index: number, item: Passeport) {
        return item.id;
    }

    trackTypeServiceById(index: number, item: TypeService) {
        return item.id;
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-paiement-popup',
    template: ''
})
export class PaiementPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private paiementPopupService: PaiementPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.paiementPopupService
                    .open(PaiementDialogComponent as Component, params['id']);
            } else {
                this.paiementPopupService
                    .open(PaiementDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
