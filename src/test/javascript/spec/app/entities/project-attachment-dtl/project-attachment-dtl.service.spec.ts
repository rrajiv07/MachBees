import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProjectAttachmentDtlService } from 'app/entities/project-attachment-dtl/project-attachment-dtl.service';
import { IProjectAttachmentDtl, ProjectAttachmentDtl } from 'app/shared/model/project-attachment-dtl.model';

describe('Service Tests', () => {
  describe('ProjectAttachmentDtl Service', () => {
    let injector: TestBed;
    let service: ProjectAttachmentDtlService;
    let httpMock: HttpTestingController;
    let elemDefault: IProjectAttachmentDtl;
    let expectedResult: IProjectAttachmentDtl | IProjectAttachmentDtl[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProjectAttachmentDtlService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ProjectAttachmentDtl(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ProjectAttachmentDtl', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ProjectAttachmentDtl()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ProjectAttachmentDtl', () => {
        const returnedFromService = Object.assign(
          {
            fileId: 'BBBBBB',
            fileName: 'BBBBBB',
            fileType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ProjectAttachmentDtl', () => {
        const returnedFromService = Object.assign(
          {
            fileId: 'BBBBBB',
            fileName: 'BBBBBB',
            fileType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ProjectAttachmentDtl', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
