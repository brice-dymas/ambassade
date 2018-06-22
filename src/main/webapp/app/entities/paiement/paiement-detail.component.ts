import { Component, OnInit, OnDestroy } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Paiement } from './paiement.model';
import { PaiementService } from './paiement.service';

@Component({
    selector: 'jhi-paiement-detail',
    templateUrl: './paiement-detail.component.html'
})
export class PaiementDetailComponent implements OnInit, OnDestroy {

    paiement: Paiement;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private paiementService: PaiementService,
        private router: Router,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPaiements();
    }

    printPage() {
        this.router.navigateByData({
            url: ['print-recu-paiement'],
            data: this.paiement
        });
    }

    load(id) {
        this.paiementService.find(id)
            .subscribe((paiementResponse: HttpResponse<Paiement>) => {
                this.paiement = paiementResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPaiements() {
        this.eventSubscriber = this.eventManager.subscribe(
            'paiementListModification',
            (response) => this.load(this.paiement.id)
        );
    }
}
