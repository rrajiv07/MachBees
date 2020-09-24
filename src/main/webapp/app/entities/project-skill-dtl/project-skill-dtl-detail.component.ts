import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectSkillDtl } from 'app/shared/model/project-skill-dtl.model';

@Component({
  selector: 'jhi-project-skill-dtl-detail',
  templateUrl: './project-skill-dtl-detail.component.html',
})
export class ProjectSkillDtlDetailComponent implements OnInit {
  projectSkillDtl: IProjectSkillDtl | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectSkillDtl }) => (this.projectSkillDtl = projectSkillDtl));
  }

  previousState(): void {
    window.history.back();
  }
}
