import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { VisaComponent } from './visa.component';
import { VisaDetailComponent } from './visa-detail.component';
import { VisaPopupComponent } from './visa-dialog.component';
import { VisaDeletePopupComponent } from './visa-delete-dialog.component';

@Injectable()
export class VisaResolvePagingParams implements Resolve<any> {

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

export const visaRoute: Routes = [
    {
        path: 'visa',
        component: VisaComponent,
        resolve: {
            'pagingParams': VisaResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.visa.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'visa/:id',
        component: VisaDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.visa.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'visa/search',
        component: VisaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.visa.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const visaPopupRoute: Routes = [
    {
        path: 'visa-new',
        component: VisaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.visa.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'visa/:id/edit',
        component: VisaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.visa.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'visa/:id/delete',
        component: VisaDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.visa.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
