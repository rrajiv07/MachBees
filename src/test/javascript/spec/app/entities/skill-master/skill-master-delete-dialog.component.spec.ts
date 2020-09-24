import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MachBeesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { SkillMasterDeleteDialogComponent } from 'app/entities/skill-master/skill-master-delete-dialog.component';
import { SkillMasterService } from 'app/entities/skill-master/skill-master.service';

describe('Component Tests', () => {
  describe('SkillMaster Management Delete Component', () => {
    let comp: SkillMasterDeleteDialogComponent;
    let fixture: ComponentFixture<SkillMasterDeleteDialogComponent>;
    let service: SkillMasterService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MachBeesTestModule],
        declarations: [SkillMasterDeleteDialogComponent],
      })
        .overrideTemplate(SkillMasterDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SkillMasterDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SkillMasterService);
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
