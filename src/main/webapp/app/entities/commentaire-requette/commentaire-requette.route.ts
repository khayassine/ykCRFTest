import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CommentaireRequette } from 'app/shared/model/commentaire-requette.model';
import { CommentaireRequetteService } from './commentaire-requette.service';
import { CommentaireRequetteComponent } from './commentaire-requette.component';
import { CommentaireRequetteDetailComponent } from './commentaire-requette-detail.component';
import { CommentaireRequetteUpdateComponent } from './commentaire-requette-update.component';
import { CommentaireRequetteDeletePopupComponent } from './commentaire-requette-delete-dialog.component';
import { ICommentaireRequette } from 'app/shared/model/commentaire-requette.model';

@Injectable({ providedIn: 'root' })
export class CommentaireRequetteResolve implements Resolve<ICommentaireRequette> {
    constructor(private service: CommentaireRequetteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CommentaireRequette> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CommentaireRequette>) => response.ok),
                map((commentaireRequette: HttpResponse<CommentaireRequette>) => commentaireRequette.body)
            );
        }
        return of(new CommentaireRequette());
    }
}

export const commentaireRequetteRoute: Routes = [
    {
        path: 'commentaire-requette',
        component: CommentaireRequetteComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ykCrfApp.commentaireRequette.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'commentaire-requette/:id/view',
        component: CommentaireRequetteDetailComponent,
        resolve: {
            commentaireRequette: CommentaireRequetteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.commentaireRequette.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'commentaire-requette/new',
        component: CommentaireRequetteUpdateComponent,
        resolve: {
            commentaireRequette: CommentaireRequetteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.commentaireRequette.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'commentaire-requette/:id/edit',
        component: CommentaireRequetteUpdateComponent,
        resolve: {
            commentaireRequette: CommentaireRequetteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.commentaireRequette.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const commentaireRequettePopupRoute: Routes = [
    {
        path: 'commentaire-requette/:id/delete',
        component: CommentaireRequetteDeletePopupComponent,
        resolve: {
            commentaireRequette: CommentaireRequetteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ykCrfApp.commentaireRequette.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
