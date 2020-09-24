import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISkillMaster } from 'app/shared/model/skill-master.model';

@Component({
  selector: 'jhi-skill-master-detail',
  templateUrl: './skill-master-detail.component.html',
})
export class SkillMasterDetailComponent implements OnInit {
  skillMaster: ISkillMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ skillMaster }) => (this.skillMaster = skillMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
