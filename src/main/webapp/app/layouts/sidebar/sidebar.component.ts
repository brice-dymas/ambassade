import { Component, OnInit } from '@angular/core';
import { NgbModalRef, NgbCollapse } from '@ng-bootstrap/ng-bootstrap';
import { JhiLanguageHelper, Principal } from '../../shared';

@Component({
  selector: 'jhi-sidebar',
  templateUrl: './sidebar.component.html',
  styles: []
})
export class SidebarComponent implements OnInit {
    isCollapsed = true;
    isVisaCollapsed = true;
    isPaiementCollapsed = true;
    isNavbarCollapsed: boolean;
    languages: any[];
    modalRef: NgbModalRef;

    constructor(
        private languageHelper: JhiLanguageHelper,
        private principal: Principal
    ) {
        this.isNavbarCollapsed = true;
    }

    ngOnInit() {
        this.languageHelper.getAll().then((languages) => {
            this.languages = languages;
        });

    }

    collapseNavbar() {
        this.isNavbarCollapsed = true;
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    collapse() {
        this.isCollapsed = true;
        this.isVisaCollapsed = true;
        this.isPaiementCollapsed = true;
    }

}
