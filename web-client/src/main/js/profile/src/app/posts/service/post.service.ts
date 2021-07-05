import {Injectable} from '@angular/core';
import {BackendService} from "../../shared/service/backend.service";
import {Observable} from "rxjs";
import {PostTO} from "../../shared/model/to/post-t-o";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private backend: BackendService) {
  }

  public createPost(postTO: PostTO): Observable<any> {
    return this.backend.post('post', postTO, true);
  }

  public getByEmail(email: string): Observable<PostTO[]> {
    return this.backend.get<PostTO[]>('post/' + email);
  }

  public deletePost(id: number): Observable<any> {
    return this.backend.delete('post/' + id);
  }

  public updatePost(postTO: PostTO): Observable<any> {
    return this.backend.put('post', postTO);
  }
}
