import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Categorie } from './categorie.model';
import { CategorieService } from './categorie.service';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import { ExcelService } from '../../excel.services';
import {UserService} from '../../shared/user/user.service';

@Component({
    selector: 'jhi-categorie',
    templateUrl: './categorie.component.html'
})
export class CategorieComponent implements OnInit, OnDestroy {

    currentAccount: any;
    categories: Categorie[];
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
    message: any;

    constructor(
        private categorieService: CategorieService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private excelService: ExcelService,
        private userService: UserService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        const oto = this.userService.authorities();
        console.log('oto = ', oto);
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
            this.excelService = excelService;
        });
    }

    loadAll() {
        this.categorieService.query({
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()}).subscribe(
            (res: HttpResponse<Categorie[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    searchCategorie(categorie: Categorie) {
        this.categorieService.search(categorie).subscribe(
            (res: HttpResponse<Categorie[]>) => this.onSuccess(res.body, res.headers),
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
        this.router.navigate(['/categorie'], {queryParams:
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
        this.router.navigate(['/categorie', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            console.log('account = ', account);
        });
        this.registerChangeInCategories();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Categorie) {
        return item.id;
    }
    registerChangeInCategories() {
        this.eventSubscriber = this.eventManager.subscribe('categorieListModification', (response) => {
            if (typeof response.content === 'string') {
                return this.loadAll();
            }else {
                return this.searchCategorie(response.content);
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
            dataHeader: ['ambassadeApp.categorie.id', 'ambassadeApp.categorie.nomCategorie'],
            dataContent: this.categories,
            property: Object.getOwnPropertyNames(this.categories[0]),
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
        this.categories = data;
        this.message =  data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
