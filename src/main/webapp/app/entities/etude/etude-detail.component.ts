import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEtude } from 'app/shared/model/etude.model';

@Component({
    selector: 'jhi-etude-detail',
    templateUrl: './etude-detail.component.html'
})
export class EtudeDetailComponent implements OnInit {
    etude: IEtude;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ etude }) => {
            this.etude = etude;
        });
    }

    previousState() {
        window.history.back();
    }
}
