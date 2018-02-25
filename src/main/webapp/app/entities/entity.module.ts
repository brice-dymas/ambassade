import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AmbassadePasseportModule } from './passeport/passeport.module';
import { AmbassadeDonneesActeModule } from './donnees-acte/donnees-acte.module';
import { AmbassadeLivreModule } from './livre/livre.module';
import { AmbassadeCategorieModule } from './categorie/categorie.module';
import { AmbassadeRapatriementModule } from './rapatriement/rapatriement.module';
import { AmbassadeCaisseModule } from './caisse/caisse.module';
import { AmbassadeMonnaieModule } from './monnaie/monnaie.module';
import { AmbassadeMontantModule } from './montant/montant.module';
import { AmbassadeProduitModule } from './produit/produit.module';
import { AmbassadeVisaModule } from './visa/visa.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AmbassadePasseportModule,
        AmbassadeDonneesActeModule,
        AmbassadeLivreModule,
        AmbassadeCategorieModule,
        AmbassadeRapatriementModule,
        AmbassadeCaisseModule,
        AmbassadeMonnaieModule,
        AmbassadeMontantModule,
        AmbassadeProduitModule,
        AmbassadeVisaModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AmbassadeEntityModule {}
