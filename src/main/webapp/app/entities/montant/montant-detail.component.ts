import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Montant } from './montant.model';
import { MontantService } from './montant.service';

@Component({
    selector: 'jhi-montant-detail',
    templateUrl: './montant-detail.component.html'
})
export class MontantDetailComponent implements OnInit, OnDestroy {

    montant: Montant;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private montantService: MontantService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMontants();
    }

    load(id) {
        this.montantService.find(id)
            .subscribe((montantResponse: HttpResponse<Montant>) => {
                this.montant = montantResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMontants() {
        this.eventSubscriber = this.eventManager.subscribe(
            'montantListModification',
            (response) => this.load(this.montant.id)
        );
    }
}
