import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {ProjectVO} from "../model/project-v-o";
import {map} from "rxjs/operators";
import {ProjectService} from "../service/project.service";
import {ProjectMapperService} from "../mapping/project-mapper.service";

@Injectable({
  providedIn: 'root'
})
export class ProjectResolver implements Resolve<ProjectVO> {

  constructor(private projectService: ProjectService,
              private projectMapper: ProjectMapperService) {
  }


  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ProjectVO> | Promise<ProjectVO> | ProjectVO {
    return this.projectService.getByName(route.params['projectName'])
      .pipe(map(projectTO => {
        return this.projectMapper.mapToVO(projectTO)
      }));
  }
}
