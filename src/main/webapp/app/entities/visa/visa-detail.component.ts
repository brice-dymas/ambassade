import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Visa } from './visa.model';
import { VisaService } from './visa.service';
import {ConfirmationService, ResolveEmit} from '@jaspero/ng-confirmations';

@Component({
    selector: 'jhi-visa-detail',
    templateUrl: './visa-detail.component.html'
})
export class VisaDetailComponent implements OnInit, OnDestroy {

    visa: Visa;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private visaService: VisaService,
        private route: ActivatedRoute,
        private _confirmation: ConfirmationService
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInVisas();
    }

    load(id) {
        this.visaService.find(id)
            .subscribe((visaResponse: HttpResponse<Visa>) => {
                this.visa = visaResponse.body;
            });
    }

    payer() {
        this.visaService.payer(this.visa.id)
            .subscribe((visaResponse: HttpResponse<Visa>) => {
                this.visa = visaResponse.body;
            });
    }

    encours() {
        this.visaService.enCours(this.visa.id)
            .subscribe((visaResponse: HttpResponse<Visa>) => {
                this.visa = visaResponse.body;
            });
    }

    retirer() {
        this.visaService.retirer(this.visa.id)
            .subscribe((visaResponse: HttpResponse<Visa>) => {
                this.visa = visaResponse.body;
            });
    }

    pret() {
        this.visaService.pret(this.visa.id)
            .subscribe((visaResponse: HttpResponse<Visa>) => {
                this.visa = visaResponse.body;
            });
    }

    payerVisa(resolved: boolean) {
        if (resolved === true) {
        this.visaService.payer(this.visa.id)
            .subscribe((visaResponse: HttpResponse<Visa>) => {
                this.visa = visaResponse.body;
            });
    }
    }

    encoursVisa(resolved: boolean) {
        if (resolved === true) {
        this.visaService.enCours(this.visa.id)
            .subscribe((visaResponse: HttpResponse<Visa>) => {
                this.visa = visaResponse.body;
            });
    }
    }

    retirerVisa(resolved: boolean) {
        if (resolved === true) {
        this.visaService.retirer(this.visa.id)
            .subscribe((visaResponse: HttpResponse<Visa>) => {
                this.visa = visaResponse.body;
            });
    }
    }

    pretVisa(resolved: boolean) {
        if (resolved === true) {
        this.visaService.pret(this.visa.id)
            .subscribe((visaResponse: HttpResponse<Visa>) => {
                this.visa = visaResponse.body;
            });
    }
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInVisas() {
        this.eventSubscriber = this.eventManager.subscribe(
            'visaListModification',
            (response) => this.load(this.visa.id)
        );
    }

    create() {
        this.open(this.visa);
        // console.log('entree dans la methode create de BUTTON');
        // const val = this.form.getRawValue();
        // const options = Object.assign({}, val);
        //
        // delete options.message;
        // delete options.title;
        // const options: ConfirmSettings = null;
        // options.confirmText = 'Oui je veux';
        // options.declineText = 'Non je veux pas';
        // options.showCloseButton(true);

        // this.open('Verification de securite', 'Voulez-vous vraiment Effectuer cette tache ?');
    }

    open(visa: Visa) {
        if (visa.state.toLocaleString() === 'NOUVEAU') {
            this._confirmation.create('Vérification de sécurité',
                'Voulez-vous vraiment marquer ce visa comme étant Payé ?').subscribe((ans: ResolveEmit) => this.payerVisa(ans.resolved));
        }
        if (visa.state.toLocaleString() === 'PAYE') {
            this._confirmation.create('Vérification de sécurité',
                'Voulez-vous vraiment marquer ce visa comme étant en cours de traitement ? ').subscribe((ans: ResolveEmit) => this.encoursVisa(ans.resolved));
        }
        if (visa.state.toLocaleString() === 'ENCOURS') {
            this._confirmation.create('Vérification de sécurité',
                'Voulez-vous vraiment marquer ce visa comme étant prêt a être retiré ?').subscribe((ans: ResolveEmit) => this.pretVisa(ans.resolved));
        }
        if (visa.state.toLocaleString() === 'PRET') {
            this._confirmation.create('Vérification de sécurité',
                'Voulez-vous vraiment marquer ce visa comme étant retiré ?').subscribe((ans: ResolveEmit) => this.retirerVisa(ans.resolved));
        }
    }
}
