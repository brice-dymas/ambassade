import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    TypeEntreeService,
    TypeEntreePopupService,
    TypeEntreeComponent,
    TypeEntreeDetailComponent,
    TypeEntreeDialogComponent,
    TypeEntreePopupComponent,
    TypeEntreeDeletePopupComponent,
    TypeEntreeDeleteDialogComponent,
    typeEntreeRoute,
    typeEntreePopupRoute,
    TypeEntreeResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...typeEntreeRoute,
    ...typeEntreePopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TypeEntreeComponent,
        TypeEntreeDetailComponent,
        TypeEntreeDialogComponent,
        TypeEntreeDeleteDialogComponent,
        TypeEntreePopupComponent,
        TypeEntreeDeletePopupComponent,
    ],
    entryComponents: [
        TypeEntreeComponent,
        TypeEntreeDialogComponent,
        TypeEntreePopupComponent,
        TypeEntreeDeleteDialogComponent,
        TypeEntreeDeletePopupComponent,
    ],
    providers: [
        TypeEntreeService,
        TypeEntreePopupService,
        TypeEntreeResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeTypeEntreeModule {}
