import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectFeatureMasterDetailComponent } from 'app/entities/project-feature-master/project-feature-master-detail.component';
import { ProjectFeatureMaster } from 'app/shared/model/project-feature-master.model';

describe('Component Tests', () => {
  describe('ProjectFeatureMaster Management Detail Component', () => {
    let comp: ProjectFeatureMasterDetailComponent;
    let fixture: ComponentFixture<ProjectFeatureMasterDetailComponent>;
    const route = ({ data: of({ projectFeatureMaster: new ProjectFeatureMaster(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectFeatureMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectFeatureMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectFeatureMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectFeatureMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectFeatureMaster).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
