import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectTypeMaster } from 'app/shared/model/project-type-master.model';

@Component({
  selector: 'jhi-project-type-master-detail',
  templateUrl: './project-type-master-detail.component.html',
})
export class ProjectTypeMasterDetailComponent implements OnInit {
  projectTypeMaster: IProjectTypeMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectTypeMaster }) => (this.projectTypeMaster = projectTypeMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
