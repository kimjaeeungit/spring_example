package com.kimjaeeun.service;

import java.util.List;

import com.kimjaeeun.domain.ReplyCriteria;
import com.kimjaeeun.domain.ReplyVo;

public interface ReplyService {
	void register(ReplyVo vo);//트랜잭션
	ReplyVo get(Long rno); //댓글 보기
	boolean modify(ReplyVo vo); //
	boolean remove(Long rno);
	List<ReplyVo> getList(ReplyCriteria cri,Long bno);//페이지에 따라 보여주기
	
}
