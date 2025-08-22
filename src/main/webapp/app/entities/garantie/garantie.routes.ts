import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import GarantieResolve from './route/garantie-routing-resolve.service';

const garantieRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/garantie.component').then(m => m.GarantieComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/garantie-detail.component').then(m => m.GarantieDetailComponent),
    resolve: {
      garantie: GarantieResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/garantie-update.component').then(m => m.GarantieUpdateComponent),
    resolve: {
      garantie: GarantieResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/garantie-update.component').then(m => m.GarantieUpdateComponent),
    resolve: {
      garantie: GarantieResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default garantieRoute;
