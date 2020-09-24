import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MachBeesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ProjectTypeMasterDeleteDialogComponent } from 'app/entities/project-type-master/project-type-master-delete-dialog.component';
import { ProjectTypeMasterService } from 'app/entities/project-type-master/project-type-master.service';

describe('Component Tests', () => {
  describe('ProjectTypeMaster Management Delete Component', () => {
    let comp: ProjectTypeMasterDeleteDialogComponent;
    let fixture: ComponentFixture<ProjectTypeMasterDeleteDialogComponent>;
    let service: ProjectTypeMasterService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [ProjectTypeMasterDeleteDialogComponent],
      })
        .overrideTemplate(ProjectTypeMasterDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectTypeMasterDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectTypeMasterService);
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
