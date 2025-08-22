import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import ContratResolve from './route/contrat-routing-resolve.service';

const contratRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/contrat.component').then(m => m.ContratComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/contrat-detail.component').then(m => m.ContratDetailComponent),
    resolve: {
      contrat: ContratResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/contrat-update.component').then(m => m.ContratUpdateComponent),
    resolve: {
      contrat: ContratResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/contrat-update.component').then(m => m.ContratUpdateComponent),
    resolve: {
      contrat: ContratResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default contratRoute;
