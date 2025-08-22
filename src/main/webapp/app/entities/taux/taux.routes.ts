import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import TauxResolve from './route/taux-routing-resolve.service';

const tauxRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/taux.component').then(m => m.TauxComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/taux-detail.component').then(m => m.TauxDetailComponent),
    resolve: {
      taux: TauxResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/taux-update.component').then(m => m.TauxUpdateComponent),
    resolve: {
      taux: TauxResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/taux-update.component').then(m => m.TauxUpdateComponent),
    resolve: {
      taux: TauxResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default tauxRoute;
