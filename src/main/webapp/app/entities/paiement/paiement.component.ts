import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Paiement } from './paiement.model';
import { PaiementService } from './paiement.service';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {PaiementSearchModel} from './paiement-search.model';

@Component({
    selector: 'jhi-paiement',
    templateUrl: './paiement.component.html'
})
export class PaiementComponent implements OnInit, OnDestroy {

currentAccount: any;
    paiements: Paiement[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private paiementService: PaiementService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.paiementService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()}).subscribe(
                (res: HttpResponse<Paiement[]>) => this.onSuccess(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    printPage() {
        this.router.navigateByData({
            url: ['/print/paiement'],
            data: this.paiements
        });
    }
    transition() {
        this.router.navigate(['/paiement'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }
    searchPaiement(paiement: PaiementSearchModel) {
        this.paiementService.search(paiement).subscribe(
            (res: HttpResponse<Paiement[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    clear() {
        this.page = 0;
        this.router.navigate(['/paiement', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInPaiements();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Paiement) {
        return item.id;
    }
    registerChangeInPaiements() {
        // this.eventSubscriber = this.eventManager.subscribe('paiementListModification', (response) => this.loadAll());
        this.eventSubscriber = this.eventManager.subscribe('paiementListModification', (response) => {
            console.log(response);
            if (typeof response.content === 'string') {
                return this.loadAll();
            }else {
                return this.searchPaiement(response.content);
            }
        });
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.paiements = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
