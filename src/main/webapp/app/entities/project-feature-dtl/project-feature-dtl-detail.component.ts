import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectFeatureDtl } from 'app/shared/model/project-feature-dtl.model';

@Component({
  selector: 'jhi-project-feature-dtl-detail',
  templateUrl: './project-feature-dtl-detail.component.html',
})
export class ProjectFeatureDtlDetailComponent implements OnInit {
  projectFeatureDtl: IProjectFeatureDtl | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectFeatureDtl }) => (this.projectFeatureDtl = projectFeatureDtl));
  }

  previousState(): void {
    window.history.back();
  }
}
