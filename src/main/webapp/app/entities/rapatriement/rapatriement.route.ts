import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { RapatriementComponent } from './rapatriement.component';
import { RapatriementDetailComponent } from './rapatriement-detail.component';
import { RapatriementPopupComponent } from './rapatriement-dialog.component';
import { RapatriementDeletePopupComponent } from './rapatriement-delete-dialog.component';

@Injectable()
export class RapatriementResolvePagingParams implements Resolve<any> {

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

export const rapatriementRoute: Routes = [
    {
        path: 'rapatriement',
        component: RapatriementComponent,
        resolve: {
            'pagingParams': RapatriementResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.rapatriement.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'rapatriement/:id',
        component: RapatriementDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.rapatriement.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rapatriementPopupRoute: Routes = [
    {
        path: 'rapatriement-new',
        component: RapatriementPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.rapatriement.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'rapatriement/:id/edit',
        component: RapatriementPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.rapatriement.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'rapatriement/:id/delete',
        component: RapatriementDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.rapatriement.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
