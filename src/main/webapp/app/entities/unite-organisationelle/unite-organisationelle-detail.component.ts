import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { UniteOrganisationelle } from './unite-organisationelle.model';
import { UniteOrganisationelleService } from './unite-organisationelle.service';

@Component({
    selector: 'jhi-unite-organisationelle-detail',
    templateUrl: './unite-organisationelle-detail.component.html'
})
export class UniteOrganisationelleDetailComponent implements OnInit, OnDestroy {

    uniteOrganisationelle: UniteOrganisationelle;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private uniteOrganisationelleService: UniteOrganisationelleService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUniteOrganisationelles();
    }

    load(id) {
        this.uniteOrganisationelleService.find(id)
            .subscribe((uniteOrganisationelleResponse: HttpResponse<UniteOrganisationelle>) => {
                this.uniteOrganisationelle = uniteOrganisationelleResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUniteOrganisationelles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'uniteOrganisationelleListModification',
            (response) => this.load(this.uniteOrganisationelle.id)
        );
    }
}
