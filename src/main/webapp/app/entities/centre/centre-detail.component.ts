import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICentre } from 'app/shared/model/centre.model';

@Component({
    selector: 'jhi-centre-detail',
    templateUrl: './centre-detail.component.html'
})
export class CentreDetailComponent implements OnInit {
    centre: ICentre;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ centre }) => {
            this.centre = centre;
        });
    }

    previousState() {
        window.history.back();
    }
}
