import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISousRegion } from 'app/shared/model/sous-region.model';

@Component({
    selector: 'jhi-sous-region-detail',
    templateUrl: './sous-region-detail.component.html'
})
export class SousRegionDetailComponent implements OnInit {
    sousRegion: ISousRegion;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sousRegion }) => {
            this.sousRegion = sousRegion;
        });
    }

    previousState() {
        window.history.back();
    }
}
