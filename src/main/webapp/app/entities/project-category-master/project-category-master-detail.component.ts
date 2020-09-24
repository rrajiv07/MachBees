import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectCategoryMaster } from 'app/shared/model/project-category-master.model';

@Component({
  selector: 'jhi-project-category-master-detail',
  templateUrl: './project-category-master-detail.component.html',
})
export class ProjectCategoryMasterDetailComponent implements OnInit {
  projectCategoryMaster: IProjectCategoryMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectCategoryMaster }) => (this.projectCategoryMaster = projectCategoryMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
