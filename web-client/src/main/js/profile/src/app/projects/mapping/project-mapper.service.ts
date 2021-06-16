import { Injectable } from '@angular/core';
import {ProjectTO} from "../../commons/model/to/project-t-o";
import {ProjectVO} from "../model/project-v-o";

@Injectable({
  providedIn: 'root'
})
export class ProjectMapperService {

  constructor() { }

  public mapToVO(projectTO: ProjectTO): ProjectVO {
    return new ProjectVO(projectTO.name);
  }
}
