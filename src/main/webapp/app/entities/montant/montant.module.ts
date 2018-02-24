import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    MontantService,
    MontantPopupService,
    MontantComponent,
    MontantDetailComponent,
    MontantDialogComponent,
    MontantPopupComponent,
    MontantDeletePopupComponent,
    MontantDeleteDialogComponent,
    montantRoute,
    montantPopupRoute,
    MontantResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...montantRoute,
    ...montantPopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MontantComponent,
        MontantDetailComponent,
        MontantDialogComponent,
        MontantDeleteDialogComponent,
        MontantPopupComponent,
        MontantDeletePopupComponent,
    ],
    entryComponents: [
        MontantComponent,
        MontantDialogComponent,
        MontantPopupComponent,
        MontantDeleteDialogComponent,
        MontantDeletePopupComponent,
    ],
    providers: [
        MontantService,
        MontantPopupService,
        MontantResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeMontantModule {}
