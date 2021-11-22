package com.kimjaeeun.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data //setter,getter,tostring등
@Alias("board") //매퍼파일에서 별칭으로 해당 클래스 매핑해줌
public class BoardVo {
	private Long bno; //글번호
	private String title; //글제목
	private String content; //글내용
	private String writer; //작성자
	private Date regDate; //글 작성시간
	private Date updateDate; //글 수정시간
	
	private int replyCnt; //댓글수
	
	@Autowired 
	private List<AttachVo> attachs =new ArrayList<>();
}
