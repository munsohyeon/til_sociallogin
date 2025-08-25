package kr.co.wikibook.greengram.application.feed.model;

import kr.co.wikibook.greengram.application.feedcomment.model.FeedCommentGetRes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeedGetRes {
     private long feedId;
     private String contents;
     private String location;
     private String createdAt;
     private long writerUserId;
     private String writerUid;
     private String writerNickName;
     private String writerPic;
     private int isLike; // 0: 좋아요 아닌 피드, 1: 좋아요한 피드
     private int likeCount; // 좋아요 수
     private List<String> pics;

     private FeedCommentGetRes comments;
}
