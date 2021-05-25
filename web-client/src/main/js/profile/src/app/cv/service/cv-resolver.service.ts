import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {CvVo} from "../model/cv-vo";
import {Observable} from "rxjs";
import {CvService} from "./cv.service";
import {map} from "rxjs/operators";
import {CvMapperService} from "../../users/mapping/cv-mapper.service";

@Injectable({
  providedIn: 'root'
})
export class CvResolverService implements Resolve<CvVo> {

  constructor(private cvService: CvService,
              private cvMapper: CvMapperService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CvVo> | Promise<CvVo> | CvVo {
    return this.cvService.getCvByUsername(route.params['username']).pipe(map(cvTO => this.cvMapper.mapToVO(cvTO)));
  }
}
