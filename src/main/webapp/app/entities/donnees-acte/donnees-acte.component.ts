import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { DonneesActe } from './donnees-acte.model';
import { DonneesActeSearchModel } from './donnees-acte-search.model';
import { DonneesActeService } from './donnees-acte.service';
import { ITEMS_PER_PAGE, Principal } from '../../shared';

@Component({
    selector: 'jhi-donnees-acte',
    templateUrl: './donnees-acte.component.html'
})
export class DonneesActeComponent implements OnInit, OnDestroy {

currentAccount: any;
    donneesActes: DonneesActe[];
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
        private donneesActeService: DonneesActeService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
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
        this.donneesActeService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()}).subscribe(
                (res: HttpResponse<DonneesActe[]>) => this.onSuccess(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    searchDonneesActe(donneesActe: DonneesActeSearchModel) {
        this.donneesActeService.search(donneesActe).subscribe(
            (res: HttpResponse<DonneesActe[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }
    transition() {
        this.router.navigate(['/donnees-acte'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate(['/donnees-acte', {
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
        this.registerChangeInDonneesActes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: DonneesActe) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInDonneesActes() {
        // this.eventSubscriber = this.eventManager.subscribe('donneesActeListModification', (response) => this.loadAll());
        this.eventSubscriber = this.eventManager.subscribe('donneesActeListModification', (response) => {
            if (typeof response.content === 'string') {
                return this.loadAll();
            }else {
                return this.searchDonneesActe(response.content);
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

    printPage() {
        const callVerbose: {
            dataHeader: any;
            dataContent: any;
            property: any;
        } = {
            dataHeader: ['ambassadeApp.donneesActe.reference',
                'ambassadeApp.donneesActe.dateDuJourChiffre',
                'ambassadeApp.donneesActe.nomEnfant',
                'ambassadeApp.donneesActe.villeNaissance',
                'ambassadeApp.donneesActe.annee',
                'ambassadeApp.donneesActe.nomPere',
                'ambassadeApp.donneesActe.prenomPere',
                'ambassadeApp.donneesActe.adressePere',
                'ambassadeApp.donneesActe.nomMere',
                'ambassadeApp.donneesActe.prenomMere',
                'ambassadeApp.donneesActe.adresseMere',
                'ambassadeApp.donneesActe.juridiction'],
            dataContent: this.donneesActes,
            property: ['reference',
                'dateDuJourChiffre',
                'nomEnfant',
                'villeNaissance',
                'annee',
                'nomPere',
                'prenomPere',
                'adressePere',
                'nomMere',
                'prenomMere',
                'adresseMere',
                'juridiction'],
            // property: Object.getOwnPropertyNames(this.donneesActes[0]),
        };
        this.router.navigateByData({
            url: ['/print'],
            // data: this.categories
            data: callVerbose
        });
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.donneesActes = data;
        console.log('donneesActes', this.donneesActes);
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
