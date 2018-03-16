import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AmbassadeSharedModule } from '../../shared';
import {
    CategorieService,
    CategoriePopupService,
    CategorieComponent,
    CategorieDetailComponent,
    CategorieDialogComponent,
    CategoriePopupComponent,
    CategorieDeletePopupComponent,
    CategorieDeleteDialogComponent,
    categorieRoute,
    categoriePopupRoute,
    CategorieResolvePagingParams,
} from './';
import {CategorieSearchComponent} from './categorie-search.component';

const ENTITY_STATES = [
    ...categorieRoute,
    ...categoriePopupRoute,
];

@NgModule({
    imports: [
        AmbassadeSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CategorieComponent,
        CategorieDetailComponent,
        CategorieDialogComponent,
        CategorieDeleteDialogComponent,
        CategoriePopupComponent,
        CategorieDeletePopupComponent,
        CategorieSearchComponent
    ],
    entryComponents: [
        CategorieComponent,
        CategorieDialogComponent,
        CategoriePopupComponent,
        CategorieDeleteDialogComponent,
        CategorieDeletePopupComponent,
    ],
    providers: [
        CategorieService,
        CategoriePopupService,
        CategorieResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeCategorieModule {}
