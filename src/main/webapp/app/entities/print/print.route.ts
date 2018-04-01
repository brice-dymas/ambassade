import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PrintComponent } from './print.component';

export const printRoute: Routes = [
    {
        path: 'print',
        component: PrintComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ambassadeApp.categorie.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
