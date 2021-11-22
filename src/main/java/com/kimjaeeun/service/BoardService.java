package com.kimjaeeun.service;

import java.util.List;

import com.kimjaeeun.domain.AttachVo;
import com.kimjaeeun.domain.BoardVo;
import com.kimjaeeun.domain.Criteria;

public interface BoardService {
	void register(BoardVo boardVo); //게시글 작성
	BoardVo get(Long bno); //게시글 상세보기
	boolean modify(BoardVo boardVo); //게시글 수정
	boolean remove(Long bno); //게시글 삭제시 첨부파일도 삭제
	List<BoardVo> getList(Criteria cri); //게시글 목록
	int getTotal(Criteria cri); //검색한 게시글 갯수 또는 게시글 개수
	
	List<AttachVo>getAttachs(Long bno);
	
}
