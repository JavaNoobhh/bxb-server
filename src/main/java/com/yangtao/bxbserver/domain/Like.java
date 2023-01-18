package com.yangtao.bxbserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    private Integer likeId;
    private Integer likeCommentId;
    private Integer likeUserId;
}
