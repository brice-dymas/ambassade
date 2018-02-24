import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Livre } from './livre.model';
import { LivreService } from './livre.service';

@Component({
    selector: 'jhi-livre-detail',
    templateUrl: './livre-detail.component.html'
})
export class LivreDetailComponent implements OnInit, OnDestroy {

    livre: Livre;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private livreService: LivreService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLivres();
    }

    load(id) {
        this.livreService.find(id)
            .subscribe((livreResponse: HttpResponse<Livre>) => {
                this.livre = livreResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLivres() {
        this.eventSubscriber = this.eventManager.subscribe(
            'livreListModification',
            (response) => this.load(this.livre.id)
        );
    }
}
