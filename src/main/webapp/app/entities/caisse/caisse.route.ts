import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { CaisseComponent } from './caisse.component';
import { CaisseDetailComponent } from './caisse-detail.component';
import { CaissePopupComponent } from './caisse-dialog.component';
import { CaisseDeletePopupComponent } from './caisse-delete-dialog.component';
import {CaisseSearchComponent} from './caisse-search.component';
import {PrintComponent} from '../print/print.component';

@Injectable()
export class CaisseResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const caisseRoute: Routes = [
    {
        path: 'caisse',
        component: CaisseComponent,
        resolve: {
            'pagingParams': CaisseResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'caisse/:id',
        component: CaisseDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'caisse/search',
        component: CaisseSearchComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'print',
        component: PrintComponent,
        data: {
            authorities: ['ROLE_CAISSE_MANAGER'],
            pageTitle: 'ambassadeApp.categorie.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const caissePopupRoute: Routes = [
    {
        path: 'caisse-new',
        component: CaissePopupComponent,
        data: {
            authorities: ['ROLE_CAISSE_MANAGER'],
            pageTitle: 'ambassadeApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'caisse/:id/edit',
        component: CaissePopupComponent,
        data: {
            authorities: ['ROLE_CAISSE_MANAGER'],
            pageTitle: 'ambassadeApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'caisse/:id/delete',
        component: CaisseDeletePopupComponent,
        data: {
            authorities: ['ROLE_CAISSE_MANAGER'],
            pageTitle: 'ambassadeApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
