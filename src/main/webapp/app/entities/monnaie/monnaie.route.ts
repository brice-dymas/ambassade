import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { MonnaieComponent } from './monnaie.component';
import { MonnaieDetailComponent } from './monnaie-detail.component';
import { MonnaiePopupComponent } from './monnaie-dialog.component';
import { MonnaieDeletePopupComponent } from './monnaie-delete-dialog.component';

@Injectable()
export class MonnaieResolvePagingParams implements Resolve<any> {

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

export const monnaieRoute: Routes = [
    {
        path: 'monnaie',
        component: MonnaieComponent,
        resolve: {
            'pagingParams': MonnaieResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.monnaie.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'monnaie/:id',
        component: MonnaieDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.monnaie.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const monnaiePopupRoute: Routes = [
    {
        path: 'monnaie-new',
        component: MonnaiePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.monnaie.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'monnaie/:id/edit',
        component: MonnaiePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.monnaie.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'monnaie/:id/delete',
        component: MonnaieDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.monnaie.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
