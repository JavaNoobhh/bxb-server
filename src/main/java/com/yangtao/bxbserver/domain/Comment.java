package com.yangtao.bxbserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer commentId;
    private String commentUserName;
    private String commentKind;
    private String commentChannel;
    private String commentUserAvatar;
    private String commentContent;
    private String commentTime;
    private int commentLikeCount;
    private int[] commentLikeUser;
}
