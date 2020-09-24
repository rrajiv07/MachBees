import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MachBeesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ProjectFeatureDtlDeleteDialogComponent } from 'app/entities/project-feature-dtl/project-feature-dtl-delete-dialog.component';
import { ProjectFeatureDtlService } from 'app/entities/project-feature-dtl/project-feature-dtl.service';

describe('Component Tests', () => {
  describe('ProjectFeatureDtl Management Delete Component', () => {
    let comp: ProjectFeatureDtlDeleteDialogComponent;
    let fixture: ComponentFixture<ProjectFeatureDtlDeleteDialogComponent>;
    let service: ProjectFeatureDtlService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectFeatureDtlDeleteDialogComponent],
      })
        .overrideTemplate(ProjectFeatureDtlDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectFeatureDtlDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectFeatureDtlService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
