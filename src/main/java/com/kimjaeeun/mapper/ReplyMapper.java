package com.kimjaeeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kimjaeeun.domain.Criteria;
import com.kimjaeeun.domain.ReplyCriteria;
import com.kimjaeeun.domain.ReplyVo;

public interface ReplyMapper {
	int insert(ReplyVo vo);//트랜잭션 댓글작성
	ReplyVo read(Long rno); //댓글 보기
	int update(ReplyVo vo); //댓글수정
	int delete(Long rno); //댓글 삭제
	List<ReplyVo> getList(@Param("bno") Long bno,@Param("cri") ReplyCriteria cri); //페이지에 따른 댓글 보여주기
}
