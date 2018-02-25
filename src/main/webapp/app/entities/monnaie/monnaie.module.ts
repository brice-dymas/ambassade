import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    MonnaieService,
    MonnaiePopupService,
    MonnaieComponent,
    MonnaieDetailComponent,
    MonnaieDialogComponent,
    MonnaiePopupComponent,
    MonnaieDeletePopupComponent,
    MonnaieDeleteDialogComponent,
    monnaieRoute,
    monnaiePopupRoute,
    MonnaieResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...monnaieRoute,
    ...monnaiePopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MonnaieComponent,
        MonnaieDetailComponent,
        MonnaieDialogComponent,
        MonnaieDeleteDialogComponent,
        MonnaiePopupComponent,
        MonnaieDeletePopupComponent,
    ],
    entryComponents: [
        MonnaieComponent,
        MonnaieDialogComponent,
        MonnaiePopupComponent,
        MonnaieDeleteDialogComponent,
        MonnaieDeletePopupComponent,
    ],
    providers: [
        MonnaieService,
        MonnaiePopupService,
        MonnaieResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeMonnaieModule {}
