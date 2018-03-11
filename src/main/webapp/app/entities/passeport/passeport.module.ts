import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    PasseportService,
    PasseportPopupService,
    PasseportComponent,
    PasseportDetailComponent,
    PasseportDialogComponent,
    PasseportPopupComponent,
    PasseportDeletePopupComponent,
    PasseportDeleteDialogComponent,
    passeportRoute,
    passeportPopupRoute,
    PasseportResolvePagingParams,
} from './';
import {PasseportSearchComponent} from './passeport-search.component';

const ENTITY_STATES = [
    ...passeportRoute,
    ...passeportPopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PasseportComponent,
        PasseportDetailComponent,
        PasseportDialogComponent,
        PasseportDeleteDialogComponent,
        PasseportPopupComponent,
        PasseportDeletePopupComponent,
        PasseportSearchComponent,
    ],
    entryComponents: [
        PasseportComponent,
        PasseportDialogComponent,
        PasseportPopupComponent,
        PasseportDeleteDialogComponent,
        PasseportDeletePopupComponent,
    ],
    providers: [
        PasseportService,
        PasseportPopupService,
        PasseportResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadePasseportModule {}
