import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { DonneesActeComponent } from './donnees-acte.component';
import { DonneesActeDetailComponent } from './donnees-acte-detail.component';
import { DonneesActePopupComponent } from './donnees-acte-dialog.component';
import { DonneesActeDeletePopupComponent } from './donnees-acte-delete-dialog.component';

@Injectable()
export class DonneesActeResolvePagingParams implements Resolve<any> {

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

export const donneesActeRoute: Routes = [
    {
        path: 'donnees-acte',
        component: DonneesActeComponent,
        resolve: {
            'pagingParams': DonneesActeResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.donneesActe.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'donnees-acte/:id',
        component: DonneesActeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.donneesActe.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const donneesActePopupRoute: Routes = [
    {
        path: 'donnees-acte-new',
        component: DonneesActePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.donneesActe.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'donnees-acte/:id/edit',
        component: DonneesActePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.donneesActe.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'donnees-acte/:id/delete',
        component: DonneesActeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.donneesActe.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
