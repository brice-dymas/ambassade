import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { LivreComponent } from './livre.component';
import { LivreDetailComponent } from './livre-detail.component';
import { LivrePopupComponent } from './livre-dialog.component';
import { LivreDeletePopupComponent } from './livre-delete-dialog.component';

@Injectable()
export class LivreResolvePagingParams implements Resolve<any> {

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

export const livreRoute: Routes = [
    {
        path: 'livre',
        component: LivreComponent,
        resolve: {
            'pagingParams': LivreResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.livre.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'livre/:id',
        component: LivreDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.livre.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'livre/search',
        component: LivreComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.livre.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const livrePopupRoute: Routes = [
    {
        path: 'livre-new',
        component: LivrePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.livre.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'livre/:id/edit',
        component: LivrePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.livre.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'livre/:id/delete',
        component: LivreDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.livre.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
