import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';
import { Chart } from 'frappe-charts/dist/frappe-charts.min.esm';

// declare var Chart: any;

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]

})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;

    data = [
        {
            title: 'Some Data',
            color: 'light-blue',
            values: [25, 40, 30, 35, 8, 52, 17, -4]
        },
        {
            title: 'Another Set',
            color: 'violet',
            values: [25, 50, -10, 15, 18, 32, 27, 14]
        }
    ];

    dataSet = {
        labels: ['12am-3am', '3am-6pm', '6am-9am', '9am-12am', '12pm-3pm', '3pm-6pm', '6pm-9pm', '9am-12am'],
        datasets: this.data
    };

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        console.log(Chart);
        const data = {
            labels: ['12am-3am', '3am-6pm', '6am-9am', '9am-12am', '12pm-3pm', '3pm-6pm', '6pm-9pm', '9am-12am'],
            datasets: [
                {

                    title: 'Some Data',
                    color: 'light-blue',
                    values: [25, 40, 30, 35, 8, 52, 17, -4]
                },
                {
                    title: 'Another Set',
                    color: 'violet',
                    values: [25, 50, -10, 15, 18, 32, 27, 14]
                }
            ]
        };

        const chart = new Chart('#chart', {  // or a DOM element,
            // new Chart() in case of ES6 module with above usage
            title: 'My Awesome Chart',
            data,
            type: 'axis-mixed', // or 'bar', 'line', 'scatter', 'pie', 'percentage'
            height: 250,
            colors: ['#7cd6fd', '#743ee2']
        });
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
