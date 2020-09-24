import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProjectBudgetDtl } from 'app/shared/model/project-budget-dtl.model';

@Component({
  selector: 'jhi-project-budget-dtl-detail',
  templateUrl: './project-budget-dtl-detail.component.html',
})
export class ProjectBudgetDtlDetailComponent implements OnInit {
  projectBudgetDtl: IProjectBudgetDtl | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectBudgetDtl }) => (this.projectBudgetDtl = projectBudgetDtl));
  }

  previousState(): void {
    window.history.back();
  }
}
