import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {CvVO} from "../model/cv-v-o";
import {Observable} from "rxjs";
import {CvService} from "./cv.service";
import {map} from "rxjs/operators";
import {CvMapperService} from "../mapping/cv-mapper.service";

@Injectable({
  providedIn: 'root'
})
export class CvResolverService implements Resolve<CvVO> {

  constructor(private cvService: CvService,
              private cvMapper: CvMapperService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CvVO> | Promise<CvVO> | CvVO {
    return this.cvService.getByUsername(route.params['username']).pipe(map(cvTO => this.cvMapper.mapToVO(cvTO)));
  }
}
