import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectRoleMaster } from 'app/shared/model/project-role-master.model';

@Component({
  selector: 'jhi-project-role-master-detail',
  templateUrl: './project-role-master-detail.component.html',
})
export class ProjectRoleMasterDetailComponent implements OnInit {
  projectRoleMaster: IProjectRoleMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectRoleMaster }) => (this.projectRoleMaster = projectRoleMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
