import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectSkillDtlDetailComponent } from 'app/entities/project-skill-dtl/project-skill-dtl-detail.component';
import { ProjectSkillDtl } from 'app/shared/model/project-skill-dtl.model';

describe('Component Tests', () => {
  describe('ProjectSkillDtl Management Detail Component', () => {
    let comp: ProjectSkillDtlDetailComponent;
    let fixture: ComponentFixture<ProjectSkillDtlDetailComponent>;
    const route = ({ data: of({ projectSkillDtl: new ProjectSkillDtl(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectSkillDtlDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectSkillDtlDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectSkillDtlDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectSkillDtl on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectSkillDtl).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
