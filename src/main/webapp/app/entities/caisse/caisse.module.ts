import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    CaisseService,
    CaissePopupService,
    CaisseComponent,
    CaisseDetailComponent,
    CaisseDialogComponent,
    CaissePopupComponent,
    CaisseDeletePopupComponent,
    CaisseDeleteDialogComponent,
    caisseRoute,
    caissePopupRoute,
    CaisseResolvePagingParams,
} from './';
import {CaisseSearchComponent} from './caisse-search.component';

const ENTITY_STATES = [
    ...caisseRoute,
    ...caissePopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CaisseComponent,
        CaisseDetailComponent,
        CaisseDialogComponent,
        CaisseDeleteDialogComponent,
        CaissePopupComponent,
        CaisseDeletePopupComponent,
        CaisseSearchComponent,
    ],
    entryComponents: [
        CaisseComponent,
        CaisseDialogComponent,
        CaissePopupComponent,
        CaisseDeleteDialogComponent,
        CaisseDeletePopupComponent,
    ],
    providers: [
        CaisseService,
        CaissePopupService,
        CaisseResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeCaisseModule {}
