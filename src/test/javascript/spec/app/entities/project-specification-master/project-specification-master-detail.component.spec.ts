import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MachBeesTestModule } from '../../../test.module';
import { ProjectSpecificationMasterDetailComponent } from 'app/entities/project-specification-master/project-specification-master-detail.component';
import { ProjectSpecificationMaster } from 'app/shared/model/project-specification-master.model';

describe('Component Tests', () => {
  describe('ProjectSpecificationMaster Management Detail Component', () => {
    let comp: ProjectSpecificationMasterDetailComponent;
    let fixture: ComponentFixture<ProjectSpecificationMasterDetailComponent>;
    const route = ({ data: of({ projectSpecificationMaster: new ProjectSpecificationMaster(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectSpecificationMasterDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectSpecificationMasterDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectSpecificationMasterDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectSpecificationMaster on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectSpecificationMaster).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
