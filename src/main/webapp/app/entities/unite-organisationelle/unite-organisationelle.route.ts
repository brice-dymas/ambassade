import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { UniteOrganisationelleComponent } from './unite-organisationelle.component';
import { UniteOrganisationelleDetailComponent } from './unite-organisationelle-detail.component';
import { UniteOrganisationellePopupComponent } from './unite-organisationelle-dialog.component';
import { UniteOrganisationelleDeletePopupComponent } from './unite-organisationelle-delete-dialog.component';

@Injectable()
export class UniteOrganisationelleResolvePagingParams implements Resolve<any> {

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

export const uniteOrganisationelleRoute: Routes = [
    {
        path: 'unite-organisationelle',
        component: UniteOrganisationelleComponent,
        resolve: {
            'pagingParams': UniteOrganisationelleResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.uniteOrganisationelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'unite-organisationelle/:id',
        component: UniteOrganisationelleDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.uniteOrganisationelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const uniteOrganisationellePopupRoute: Routes = [
    {
        path: 'unite-organisationelle-new',
        component: UniteOrganisationellePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.uniteOrganisationelle.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'unite-organisationelle/:id/edit',
        component: UniteOrganisationellePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.uniteOrganisationelle.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'unite-organisationelle/:id/delete',
        component: UniteOrganisationelleDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.uniteOrganisationelle.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
