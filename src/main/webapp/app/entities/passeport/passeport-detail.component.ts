import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpResponse} from '@angular/common/http';
import {Subscription} from 'rxjs/Subscription';
import {JhiEventManager, JhiDataUtils} from 'ng-jhipster';

import {Passeport, State} from './passeport.model';
import {PasseportService} from './passeport.service';
import {Router} from '@angular/router';
import {ConfirmationService, ResolveEmit} from '@jaspero/ng-confirmations';

@Component({
    selector: 'jhi-passeport-detail',
    templateUrl: './passeport-detail.component.html'
})
export class PasseportDetailComponent implements OnInit, OnDestroy {

    passeport: Passeport;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(private eventManager: JhiEventManager,
                private dataUtils: JhiDataUtils,
                private passeportService: PasseportService,
                private router: Router,
                private route: ActivatedRoute,
                private _confirmation: ConfirmationService) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPasseports();
    }

    load(id) {
        this.passeportService.find(id)
            .subscribe((passeportResponse: HttpResponse<Passeport>) => {
                this.passeport = passeportResponse.body;
            });
    }

    payer() {
        this.passeportService.payer(this.passeport.id)
            .subscribe((passeportResponse: HttpResponse<Passeport>) => {
                this.passeport = passeportResponse.body;
            });
    }

    payerPasseport(resolved: boolean) {
        if (resolved === true) {
            this.passeportService.payer(this.passeport.id)
                .subscribe((passeportResponse: HttpResponse<Passeport>) => {
                    this.passeport = passeportResponse.body;
                });
        } else {
            alert('Procedure Annuleee');
        }
    }

    encoursPasseport(resolved: boolean) {
        if (resolved === true) {
            this.passeportService.enCours(this.passeport.id)
                .subscribe((passeportResponse: HttpResponse<Passeport>) => {
                    this.passeport = passeportResponse.body;
                });
        }
    }

    retirerPasseport(resolved: boolean) {
        if (resolved === true) {
            this.passeportService.retirer(this.passeport.id)
                .subscribe((passeportResponse: HttpResponse<Passeport>) => {
                    this.passeport = passeportResponse.body;
                });
        }
    }

    pretPasseport(resolved: boolean) {
        if (resolved === true) {
            this.passeportService.pret(this.passeport.id)
                .subscribe((passeportResponse: HttpResponse<Passeport>) => {
                    this.passeport = passeportResponse.body;
                });
        }
    }

    encours() {
        this.passeportService.enCours(this.passeport.id)
            .subscribe((passeportResponse: HttpResponse<Passeport>) => {
                this.passeport = passeportResponse.body;
            });
    }

    retirer() {
        this.passeportService.retirer(this.passeport.id)
            .subscribe((passeportResponse: HttpResponse<Passeport>) => {
                this.passeport = passeportResponse.body;
            });
    }

    pret() {
        this.passeportService.pret(this.passeport.id)
            .subscribe((passeportResponse: HttpResponse<Passeport>) => {
                this.passeport = passeportResponse.body;
            });
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

    registerChangeInPasseports() {
        this.eventSubscriber = this.eventManager.subscribe(
            'passeportListModification',
            (response) => this.load(this.passeport.id)
        );
    }

    printPage() {
        this.router.navigateByData({
            url: ['/print/detail-passeport'],
            data: this.passeport
        });
    }

    create() {
        this.open(this.passeport);
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

    open(passeport: Passeport) {
        if (passeport.state.toLocaleString() === 'NOUVEAU') {
            this._confirmation.create('Vérification de sécurité',
                'Voulez-vous vraiment marquer ce passeport comme étant Payé ?').subscribe((ans: ResolveEmit) => this.payerPasseport(ans.resolved));
        }
        if (passeport.state.toLocaleString() === 'PAYE') {
            this._confirmation.create('Vérification de sécurité',
                'Voulez-vous vraiment marquer ce passeport comme étant en cours de traitement ? ').subscribe((ans: ResolveEmit) => this.encoursPasseport(ans.resolved));
        }
        if (passeport.state.toLocaleString() === 'ENCOURS') {
            this._confirmation.create('Vérification de sécurité',
                'Voulez-vous vraiment marquer ce passeport comme étant prêt a être retiré ?').subscribe((ans: ResolveEmit) => this.pretPasseport(ans.resolved));
        }
        if (passeport.state.toLocaleString() === 'PRET') {
            this._confirmation.create('Vérification de sécurité',
                'Voulez-vous vraiment marquer ce passeport comme étant retiré ?').subscribe((ans: ResolveEmit) => this.retirerPasseport(ans.resolved));
        }
    }
}
