import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProjectRoleDtlService } from 'app/entities/project-role-dtl/project-role-dtl.service';
import { IProjectRoleDtl, ProjectRoleDtl } from 'app/shared/model/project-role-dtl.model';

describe('Service Tests', () => {
  describe('ProjectRoleDtl Service', () => {
    let injector: TestBed;
    let service: ProjectRoleDtlService;
    let httpMock: HttpTestingController;
    let elemDefault: IProjectRoleDtl;
    let expectedResult: IProjectRoleDtl | IProjectRoleDtl[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProjectRoleDtlService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ProjectRoleDtl(0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ProjectRoleDtl', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ProjectRoleDtl()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ProjectRoleDtl', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ProjectRoleDtl', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ProjectRoleDtl', () => {
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
