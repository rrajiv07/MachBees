import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectHdr } from 'app/shared/model/project-hdr.model';

@Component({
  selector: 'jhi-project-hdr-detail',
  templateUrl: './project-hdr-detail.component.html',
})
export class ProjectHdrDetailComponent implements OnInit {
  projectHdr: IProjectHdr | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectHdr }) => (this.projectHdr = projectHdr));
  }

  previousState(): void {
    window.history.back();
  }
}
