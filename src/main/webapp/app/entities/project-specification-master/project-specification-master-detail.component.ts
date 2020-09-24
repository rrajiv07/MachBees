import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';

@Component({
  selector: 'jhi-project-specification-master-detail',
  templateUrl: './project-specification-master-detail.component.html',
})
export class ProjectSpecificationMasterDetailComponent implements OnInit {
  projectSpecificationMaster: IProjectSpecificationMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectSpecificationMaster }) => (this.projectSpecificationMaster = projectSpecificationMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
