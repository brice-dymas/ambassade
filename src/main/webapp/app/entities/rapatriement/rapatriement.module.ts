import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    RapatriementService,
    RapatriementPopupService,
    RapatriementComponent,
    RapatriementDetailComponent,
    RapatriementDialogComponent,
    RapatriementPopupComponent,
    RapatriementDeletePopupComponent,
    RapatriementDeleteDialogComponent,
    rapatriementRoute,
    rapatriementPopupRoute,
    RapatriementResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...rapatriementRoute,
    ...rapatriementPopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        RapatriementComponent,
        RapatriementDetailComponent,
        RapatriementDialogComponent,
        RapatriementDeleteDialogComponent,
        RapatriementPopupComponent,
        RapatriementDeletePopupComponent,
    ],
    entryComponents: [
        RapatriementComponent,
        RapatriementDialogComponent,
        RapatriementPopupComponent,
        RapatriementDeleteDialogComponent,
        RapatriementDeletePopupComponent,
    ],
    providers: [
        RapatriementService,
        RapatriementPopupService,
        RapatriementResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeRapatriementModule {}
