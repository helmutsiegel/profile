import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PostService} from "../../service/post.service";
import {PostVO} from "../../model/post-v-o";
import {PostMapperService} from "../../mapping/post-mapper.service";

@Component({
  selector: 'post-page',
  templateUrl: './post-page.component.html',
  styleUrls: ['./post-page.component.css']
})
export class PostPageComponent implements OnInit {

  postVO!: PostVO;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private postService: PostService,
              private postMapper: PostMapperService) {
  }

  ngOnInit(): void {
    const postId = this.activatedRoute.snapshot.params['id'];
    this.postService.getById(postId).subscribe(postTO => {
        this.postVO = this.postMapper.mapToVO(postTO);
      },
      error => {
        this.router.navigate(['404']);
      });
  }
}
