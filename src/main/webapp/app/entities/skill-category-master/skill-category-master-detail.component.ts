import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISkillCategoryMaster } from 'app/shared/model/skill-category-master.model';

@Component({
  selector: 'jhi-skill-category-master-detail',
  templateUrl: './skill-category-master-detail.component.html',
})
export class SkillCategoryMasterDetailComponent implements OnInit {
  skillCategoryMaster: ISkillCategoryMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ skillCategoryMaster }) => (this.skillCategoryMaster = skillCategoryMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
