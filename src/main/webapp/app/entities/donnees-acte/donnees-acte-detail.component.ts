import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { DonneesActe } from './donnees-acte.model';
import { DonneesActeService } from './donnees-acte.service';

@Component({
    selector: 'jhi-donnees-acte-detail',
    templateUrl: './donnees-acte-detail.component.html'
})
export class DonneesActeDetailComponent implements OnInit, OnDestroy {

    donneesActe: DonneesActe;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private donneesActeService: DonneesActeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDonneesActes();
    }

    load(id) {
        this.donneesActeService.find(id)
            .subscribe((donneesActeResponse: HttpResponse<DonneesActe>) => {
                this.donneesActe = donneesActeResponse.body;
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

    registerChangeInDonneesActes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'donneesActeListModification',
            (response) => this.load(this.donneesActe.id)
        );
    }
}
