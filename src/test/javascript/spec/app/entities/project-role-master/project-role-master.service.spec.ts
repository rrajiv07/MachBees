import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProjectRoleMasterService } from 'app/entities/project-role-master/project-role-master.service';
import { IProjectRoleMaster, ProjectRoleMaster } from 'app/shared/model/project-role-master.model';

describe('Service Tests', () => {
  describe('ProjectRoleMaster Service', () => {
    let injector: TestBed;
    let service: ProjectRoleMasterService;
    let httpMock: HttpTestingController;
    let elemDefault: IProjectRoleMaster;
    let expectedResult: IProjectRoleMaster | IProjectRoleMaster[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProjectRoleMasterService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ProjectRoleMaster(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ProjectRoleMaster', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ProjectRoleMaster()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ProjectRoleMaster', () => {
        const returnedFromService = Object.assign(
          {
            roleCode: 'BBBBBB',
            roleName: 'BBBBBB',
            roleDescription: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ProjectRoleMaster', () => {
        const returnedFromService = Object.assign(
          {
            roleCode: 'BBBBBB',
            roleName: 'BBBBBB',
            roleDescription: 'BBBBBB',
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

      it('should delete a ProjectRoleMaster', () => {
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
