import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectRoleDtlDetailComponent } from 'app/entities/project-role-dtl/project-role-dtl-detail.component';
import { ProjectRoleDtl } from 'app/shared/model/project-role-dtl.model';

describe('Component Tests', () => {
  describe('ProjectRoleDtl Management Detail Component', () => {
    let comp: ProjectRoleDtlDetailComponent;
    let fixture: ComponentFixture<ProjectRoleDtlDetailComponent>;
    const route = ({ data: of({ projectRoleDtl: new ProjectRoleDtl(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectRoleDtlDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectRoleDtlDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectRoleDtlDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectRoleDtl on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectRoleDtl).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
