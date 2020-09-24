import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectFeatureDtlDetailComponent } from 'app/entities/project-feature-dtl/project-feature-dtl-detail.component';
import { ProjectFeatureDtl } from 'app/shared/model/project-feature-dtl.model';

describe('Component Tests', () => {
  describe('ProjectFeatureDtl Management Detail Component', () => {
    let comp: ProjectFeatureDtlDetailComponent;
    let fixture: ComponentFixture<ProjectFeatureDtlDetailComponent>;
    const route = ({ data: of({ projectFeatureDtl: new ProjectFeatureDtl(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectFeatureDtlDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectFeatureDtlDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectFeatureDtlDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectFeatureDtl on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectFeatureDtl).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
