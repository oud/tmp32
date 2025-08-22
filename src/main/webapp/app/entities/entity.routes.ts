import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'tmp32App.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'demande-xrm',
    data: { pageTitle: 'tmp32App.demandeXRM.home.title' },
    loadChildren: () => import('./demande-xrm/demande-xrm.routes'),
  },
  {
    path: 'mise-en-gestion',
    data: { pageTitle: 'tmp32App.miseEnGestion.home.title' },
    loadChildren: () => import('./mise-en-gestion/mise-en-gestion.routes'),
  },
  {
    path: 'contrat',
    data: { pageTitle: 'tmp32App.contrat.home.title' },
    loadChildren: () => import('./contrat/contrat.routes'),
  },
  {
    path: 'groupe',
    data: { pageTitle: 'tmp32App.groupe.home.title' },
    loadChildren: () => import('./groupe/groupe.routes'),
  },
  {
    path: 'produit',
    data: { pageTitle: 'tmp32App.produit.home.title' },
    loadChildren: () => import('./produit/produit.routes'),
  },
  {
    path: 'garantie',
    data: { pageTitle: 'tmp32App.garantie.home.title' },
    loadChildren: () => import('./garantie/garantie.routes'),
  },
  {
    path: 'taux',
    data: { pageTitle: 'tmp32App.taux.home.title' },
    loadChildren: () => import('./taux/taux.routes'),
  },
  {
    path: 'operation',
    data: { pageTitle: 'tmp32App.operation.home.title' },
    loadChildren: () => import('./operation/operation.routes'),
  },
  {
    path: 'pm-entreprise',
    data: { pageTitle: 'tmp32App.pmEntreprise.home.title' },
    loadChildren: () => import('./pm-entreprise/pm-entreprise.routes'),
  },
  {
    path: 'pm-etablissement',
    data: { pageTitle: 'tmp32App.pmEtablissement.home.title' },
    loadChildren: () => import('./pm-etablissement/pm-etablissement.routes'),
  },
  {
    path: 'adresse',
    data: { pageTitle: 'tmp32App.adresse.home.title' },
    loadChildren: () => import('./adresse/adresse.routes'),
  },
  {
    path: 'telephone',
    data: { pageTitle: 'tmp32App.telephone.home.title' },
    loadChildren: () => import('./telephone/telephone.routes'),
  },
  {
    path: 'email',
    data: { pageTitle: 'tmp32App.email.home.title' },
    loadChildren: () => import('./email/email.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
