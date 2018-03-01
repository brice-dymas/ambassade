import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Rapatriement } from './rapatriement.model';
import { RapatriementService } from './rapatriement.service';

@Component({
    selector: 'jhi-rapatriement-detail',
    templateUrl: './rapatriement-detail.component.html'
})
export class RapatriementDetailComponent implements OnInit, OnDestroy {

    rapatriement: Rapatriement;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private rapatriementService: RapatriementService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRapatriements();
    }

    load(id) {
        this.rapatriementService.find(id)
            .subscribe((rapatriementResponse: HttpResponse<Rapatriement>) => {
                this.rapatriement = rapatriementResponse.body;
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

    registerChangeInRapatriements() {
        this.eventSubscriber = this.eventManager.subscribe(
            'rapatriementListModification',
            (response) => this.load(this.rapatriement.id)
        );
    }
}
