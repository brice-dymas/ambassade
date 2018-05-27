import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Passeport } from './passeport.model';
import { PasseportService } from './passeport.service';

@Component({
    selector: 'jhi-passeport-detail',
    templateUrl: './passeport-detail.component.html'
})
export class PasseportDetailComponent implements OnInit, OnDestroy {

    passeport: Passeport;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private passeportService: PasseportService,
        private route: ActivatedRoute
    ) {
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
}
