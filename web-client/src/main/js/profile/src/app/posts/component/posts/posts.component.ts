import {Component, OnInit, ViewChild} from '@angular/core';
import {SimpleModalComponent} from "../../../shared/component/simple-modal/simple-modal.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Size} from "../../../shared/model/enum/size";
import {PostService} from "../../service/post.service";
import {ActivatedRoute} from "@angular/router";
import {PostTO} from "../../../shared/model/to/post-t-o";
import {AuthService} from "../../../service/auth.service";
import {ToastrService} from "../../../shared/service/toastr.service";
import {PostVO} from "../../model/post-v-o";
import {PostMapperService} from "../../mapping/post-mapper.service";
import {PostCardComponent} from "../post-card/post-card.component";


@Component({
  selector: 'posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  @ViewChild('newPostModal') newPostModal!: SimpleModalComponent;

  newPostForm!: FormGroup;
  private postTitleFC!: FormControl;
  private postContentFC!: FormControl;

  private newPostTitle!: string;
  private newPostContent!: string;

  posts!: PostVO[];
  visiblePosts: PostVO[] = [];
  tags!: Set<string>;
  selectedTags: string[] = [];

  constructor(private postService: PostService,
              private postMapper: PostMapperService,
              public activatedRoute: ActivatedRoute,
              public authService: AuthService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.loadPosts();
    this.postTitleFC = new FormControl(this.newPostTitle, Validators.required);
    this.postContentFC = new FormControl(this.newPostContent, Validators.required);
    this.newPostForm = new FormGroup({
      postTitle: this.postTitleFC,
      postContent: this.postContentFC
    })
  }

  private loadPosts(): void {
    const emailFromUrl = this.activatedRoute.snapshot.params['email'];
    this.postService.getByEmail(emailFromUrl).subscribe(posts => {
      this.posts = posts.map(postTO => this.postMapper.mapToVO(postTO))
        .sort((a, b) => b.dateCreated.localeCompare(a.dateCreated));
      this.tags = new Set(this.posts.filter(postVO => !!postVO.tags).map(postVO => postVO.tags.split(','))
        .reduce((accumulator, value) => accumulator.concat(value), []));
      this.filterPosts();
    });
  }

  public newPost(formValues: any): void {
    this.postService.createPost({
      title: formValues.postTitle,
      content: formValues.postContent
    } as PostTO).subscribe(_ => {
      this.loadPosts()
    });
    this.newPostModal.close();
  }

  public get size(): typeof Size {
    return Size;
  }

  public deletePost(id: number): void {
    if (window.confirm("Are you sure want to delete the post?")) {
      this.postService.deletePost(id).subscribe(_ => {
          this.loadPosts();
          this.toastr.success("Post deleted successfully!")
        },
        err => {
          this.toastr.error(err.error)
        });
    }
  }

  public savePost(postVO: PostVO, postCardComponent: PostCardComponent): void {
    const postTO = this.postMapper.mapToTO(postVO);
    this.postService.updatePost(postTO)
      .subscribe(_ => {
          this.loadPosts();
          this.toastr.success("Post updated successfully!");
        },
        error => {
          postCardComponent.reset();
          this.toastr.error("Post could not updated!");
        });
  }

  public changeTagFilter(tag: string): void {
    if (this.selectedTags.includes(tag)) {
      this.selectedTags.splice(this.selectedTags.indexOf(tag), 1);
    } else {
      this.selectedTags.push(tag);
    }
    this.filterPosts();
  }

  public filterPosts(): void {
    if (this.posts) {
      if (this.selectedTags.length > 0) {
        this.visiblePosts = this.posts.filter(postVO => this.selectedTags.some(selectedTag => postVO.tags.split(',').includes(selectedTag)))
      } else {
        this.visiblePosts = [...this.posts];
      }
    }
  }
}
