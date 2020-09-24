import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ProjectHdrService } from 'app/entities/project-hdr/project-hdr.service';
import { IProjectHdr, ProjectHdr } from 'app/shared/model/project-hdr.model';

describe('Service Tests', () => {
  describe('ProjectHdr Service', () => {
    let injector: TestBed;
    let service: ProjectHdrService;
    let httpMock: HttpTestingController;
    let elemDefault: IProjectHdr;
    let expectedResult: IProjectHdr | IProjectHdr[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProjectHdrService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProjectHdr(0, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', currentDate, 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            endDate: currentDate.format(DATE_FORMAT),
            createdDate: currentDate.format(DATE_FORMAT),
            modifiedDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ProjectHdr', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            endDate: currentDate.format(DATE_FORMAT),
            createdDate: currentDate.format(DATE_FORMAT),
            modifiedDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            endDate: currentDate,
            createdDate: currentDate,
            modifiedDate: currentDate,
          },
          returnedFromService
        );

        service.create(new ProjectHdr()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ProjectHdr', () => {
        const returnedFromService = Object.assign(
          {
            overview: 'BBBBBB',
            projectDescription: 'BBBBBB',
            endDate: currentDate.format(DATE_FORMAT),
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_FORMAT),
            modifiedBy: 'BBBBBB',
            modifiedDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            endDate: currentDate,
            createdDate: currentDate,
            modifiedDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ProjectHdr', () => {
        const returnedFromService = Object.assign(
          {
            overview: 'BBBBBB',
            projectDescription: 'BBBBBB',
            endDate: currentDate.format(DATE_FORMAT),
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_FORMAT),
            modifiedBy: 'BBBBBB',
            modifiedDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            endDate: currentDate,
            createdDate: currentDate,
            modifiedDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ProjectHdr', () => {
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
