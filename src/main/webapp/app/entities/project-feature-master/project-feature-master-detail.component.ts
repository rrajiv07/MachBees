import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectFeatureMaster } from 'app/shared/model/project-feature-master.model';

@Component({
  selector: 'jhi-project-feature-master-detail',
  templateUrl: './project-feature-master-detail.component.html',
})
export class ProjectFeatureMasterDetailComponent implements OnInit {
  projectFeatureMaster: IProjectFeatureMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectFeatureMaster }) => (this.projectFeatureMaster = projectFeatureMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
