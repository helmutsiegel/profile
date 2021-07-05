import {Injectable} from '@angular/core';
import {PostTO} from "../../shared/model/to/post-t-o";
import {PostVO} from "../model/post-v-o";

@Injectable({
  providedIn: 'root'
})
export class PostMapperService {

  constructor() {
  }

  public mapToVO(postTO: PostTO): PostVO {
    return new PostVO(postTO.id,
      postTO.title, postTO.content, postTO.dateCreated, postTO.tags);
  }

  public mapToTO(postVO: PostVO): PostTO {
    return {
      id: postVO.id,
      title: postVO.title,
      content: postVO.content,
      dateCreated: postVO.dateCreated,
      tags: postVO.tags
    }
  }
}
