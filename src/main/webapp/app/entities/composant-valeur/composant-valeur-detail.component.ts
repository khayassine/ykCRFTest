import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComposantValeur } from 'app/shared/model/composant-valeur.model';

@Component({
    selector: 'jhi-composant-valeur-detail',
    templateUrl: './composant-valeur-detail.component.html'
})
export class ComposantValeurDetailComponent implements OnInit {
    composantValeur: IComposantValeur;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ composantValeur }) => {
            this.composantValeur = composantValeur;
        });
    }

    previousState() {
        window.history.back();
    }
}
