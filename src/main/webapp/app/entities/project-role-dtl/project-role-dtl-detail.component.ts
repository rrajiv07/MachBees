import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectRoleDtl } from 'app/shared/model/project-role-dtl.model';

@Component({
  selector: 'jhi-project-role-dtl-detail',
  templateUrl: './project-role-dtl-detail.component.html',
})
export class ProjectRoleDtlDetailComponent implements OnInit {
  projectRoleDtl: IProjectRoleDtl | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectRoleDtl }) => (this.projectRoleDtl = projectRoleDtl));
  }

  previousState(): void {
    window.history.back();
  }
}
