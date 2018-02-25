import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Monnaie } from './monnaie.model';
import { MonnaieService } from './monnaie.service';

@Component({
    selector: 'jhi-monnaie-detail',
    templateUrl: './monnaie-detail.component.html'
})
export class MonnaieDetailComponent implements OnInit, OnDestroy {

    monnaie: Monnaie;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private monnaieService: MonnaieService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMonnaies();
    }

    load(id) {
        this.monnaieService.find(id)
            .subscribe((monnaieResponse: HttpResponse<Monnaie>) => {
                this.monnaie = monnaieResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMonnaies() {
        this.eventSubscriber = this.eventManager.subscribe(
            'monnaieListModification',
            (response) => this.load(this.monnaie.id)
        );
    }
}
