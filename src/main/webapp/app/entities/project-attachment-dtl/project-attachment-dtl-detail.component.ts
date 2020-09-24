import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectAttachmentDtl } from 'app/shared/model/project-attachment-dtl.model';

@Component({
  selector: 'jhi-project-attachment-dtl-detail',
  templateUrl: './project-attachment-dtl-detail.component.html',
})
export class ProjectAttachmentDtlDetailComponent implements OnInit {
  projectAttachmentDtl: IProjectAttachmentDtl | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectAttachmentDtl }) => (this.projectAttachmentDtl = projectAttachmentDtl));
  }

  previousState(): void {
    window.history.back();
  }
}
