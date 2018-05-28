import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TypeService } from './type-service.model';
import { TypeServiceService } from './type-service.service';

@Component({
    selector: 'jhi-type-service-detail',
    templateUrl: './type-service-detail.component.html'
})
export class TypeServiceDetailComponent implements OnInit, OnDestroy {

    typeService: TypeService;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private typeServiceService: TypeServiceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTypeServices();
    }

    load(id) {
        this.typeServiceService.find(id)
            .subscribe((typeServiceResponse: HttpResponse<TypeService>) => {
                this.typeService = typeServiceResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTypeServices() {
        this.eventSubscriber = this.eventManager.subscribe(
            'typeServiceListModification',
            (response) => this.load(this.typeService.id)
        );
    }
}
