import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { TypeEntreeComponent } from './type-entree.component';
import { TypeEntreeDetailComponent } from './type-entree-detail.component';
import { TypeEntreePopupComponent } from './type-entree-dialog.component';
import { TypeEntreeDeletePopupComponent } from './type-entree-delete-dialog.component';

@Injectable()
export class TypeEntreeResolvePagingParams implements Resolve<any> {

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

export const typeEntreeRoute: Routes = [
    {
        path: 'type-entree',
        component: TypeEntreeComponent,
        resolve: {
            'pagingParams': TypeEntreeResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.typeEntree.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'type-entree/:id',
        component: TypeEntreeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.typeEntree.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeEntreePopupRoute: Routes = [
    {
        path: 'type-entree-new',
        component: TypeEntreePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.typeEntree.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'type-entree/:id/edit',
        component: TypeEntreePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.typeEntree.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'type-entree/:id/delete',
        component: TypeEntreeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.typeEntree.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
