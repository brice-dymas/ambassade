import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TypeEntree } from './type-entree.model';
import { TypeEntreeService } from './type-entree.service';

@Component({
    selector: 'jhi-type-entree-detail',
    templateUrl: './type-entree-detail.component.html'
})
export class TypeEntreeDetailComponent implements OnInit, OnDestroy {

    typeEntree: TypeEntree;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private typeEntreeService: TypeEntreeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTypeEntrees();
    }

    load(id) {
        this.typeEntreeService.find(id)
            .subscribe((typeEntreeResponse: HttpResponse<TypeEntree>) => {
                this.typeEntree = typeEntreeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTypeEntrees() {
        this.eventSubscriber = this.eventManager.subscribe(
            'typeEntreeListModification',
            (response) => this.load(this.typeEntree.id)
        );
    }
}
