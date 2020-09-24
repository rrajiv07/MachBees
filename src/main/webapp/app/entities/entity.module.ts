import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'category-metadata',
        loadChildren: () => import('./category-metadata/category-metadata.module').then(m => m.MachBeesCategoryMetadataModule),
      },
      {
        path: 'profile-master',
        loadChildren: () => import('./profile-master/profile-master.module').then(m => m.MachBeesProfileMasterModule),
      },
      {
        path: 'project-type-master',
        loadChildren: () => import('./project-type-master/project-type-master.module').then(m => m.MachBeesProjectTypeMasterModule),
      },
      {
        path: 'project-specification-master',
        loadChildren: () =>
          import('./project-specification-master/project-specification-master.module').then(
            m => m.MachBeesProjectSpecificationMasterModule
          ),
      },
      {
        path: 'project-category-master',
        loadChildren: () =>
          import('./project-category-master/project-category-master.module').then(m => m.MachBeesProjectCategoryMasterModule),
      },
      {
        path: 'project-feature-master',
        loadChildren: () =>
          import('./project-feature-master/project-feature-master.module').then(m => m.MachBeesProjectFeatureMasterModule),
      },
      {
        path: 'project-role-master',
        loadChildren: () => import('./project-role-master/project-role-master.module').then(m => m.MachBeesProjectRoleMasterModule),
      },
      {
        path: 'skill-category-master',
        loadChildren: () => import('./skill-category-master/skill-category-master.module').then(m => m.MachBeesSkillCategoryMasterModule),
      },
      {
        path: 'skill-master',
        loadChildren: () => import('./skill-master/skill-master.module').then(m => m.MachBeesSkillMasterModule),
      },
      {
        path: 'user-master',
        loadChildren: () => import('./user-master/user-master.module').then(m => m.MachBeesUserMasterModule),
      },
      {
        path: 'user-personal-details',
        loadChildren: () => import('./user-personal-details/user-personal-details.module').then(m => m.MachBeesUserPersonalDetailsModule),
      },
      {
        path: 'user-company-details',
        loadChildren: () => import('./user-company-details/user-company-details.module').then(m => m.MachBeesUserCompanyDetailsModule),
      },
      {
        path: 'user-language-details',
        loadChildren: () => import('./user-language-details/user-language-details.module').then(m => m.MachBeesUserLanguageDetailsModule),
      },
      {
        path: 'project-hdr',
        loadChildren: () => import('./project-hdr/project-hdr.module').then(m => m.MachBeesProjectHdrModule),
      },
      {
        path: 'project-attachment-dtl',
        loadChildren: () =>
          import('./project-attachment-dtl/project-attachment-dtl.module').then(m => m.MachBeesProjectAttachmentDtlModule),
      },
      {
        path: 'project-feature-dtl',
        loadChildren: () => import('./project-feature-dtl/project-feature-dtl.module').then(m => m.MachBeesProjectFeatureDtlModule),
      },
      {
        path: 'project-role-dtl',
        loadChildren: () => import('./project-role-dtl/project-role-dtl.module').then(m => m.MachBeesProjectRoleDtlModule),
      },
      {
        path: 'project-skill-dtl',
        loadChildren: () => import('./project-skill-dtl/project-skill-dtl.module').then(m => m.MachBeesProjectSkillDtlModule),
      },
      {
        path: 'project-budget-dtl',
        loadChildren: () => import('./project-budget-dtl/project-budget-dtl.module').then(m => m.MachBeesProjectBudgetDtlModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class MachBeesEntityModule {}
