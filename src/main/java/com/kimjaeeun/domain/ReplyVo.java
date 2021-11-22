package com.kimjaeeun.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data @Alias("reply")
public class ReplyVo {
	private Long rno; //댓글번호
	private Long bno; //게시글번호
	
	private String reply; //댓글내용
	private String replyer; //댓글러
	private Date replyDate; //작성일자
	private Date updateDate; //수정일자
}
