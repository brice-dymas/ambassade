import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { MontantComponent } from './montant.component';
import { MontantDetailComponent } from './montant-detail.component';
import { MontantPopupComponent } from './montant-dialog.component';
import { MontantDeletePopupComponent } from './montant-delete-dialog.component';
import {PrintComponent} from '../print/print.component';

@Injectable()
export class MontantResolvePagingParams implements Resolve<any> {

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

export const montantRoute: Routes = [
    {
        path: 'montant',
        component: MontantComponent,
        resolve: {
            'pagingParams': MontantResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.montant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'montant/:id',
        component: MontantDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.montant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'montant/search',
        component: MontantComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.montant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'print',
        component: PrintComponent,
        data: {
            authorities: ['ROLE_MONTANT_MANAGER'],
            pageTitle: 'ambassadeApp.montant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const montantPopupRoute: Routes = [
    {
        path: 'montant-new',
        component: MontantPopupComponent,
        data: {
            authorities: ['ROLE_MONTANT_MANAGER'],
            pageTitle: 'ambassadeApp.montant.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'montant/:id/edit',
        component: MontantPopupComponent,
        data: {
            authorities: ['ROLE_MONTANT_MANAGER'],
            pageTitle: 'ambassadeApp.montant.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'montant/:id/delete',
        component: MontantDeletePopupComponent,
        data: {
            authorities: ['ROLE_MONTANT_MANAGER'],
            pageTitle: 'ambassadeApp.montant.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
