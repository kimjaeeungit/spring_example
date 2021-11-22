package com.kimjaeeun.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kimjaeeun.domain.ReplyCriteria;
import com.kimjaeeun.domain.ReplyVo;
import com.kimjaeeun.mapper.BoardMapper;
import com.kimjaeeun.mapper.ReplyMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor @Service
public class ReplyServiceImpl implements ReplyService {
	private ReplyMapper mapper;
	//댓글수 처리 위해
	private BoardMapper boardMapper;
	
	@Override
	@Transactional //이게 없으면 댓글insert가 안되도 cnt가 올라간다.
	public void register(ReplyVo vo) {
		//작업2 댓글 갯수 업데이트 작업
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		//작업1 댓글 작성잡업
		mapper.insert(vo);
	
	}

	@Override
	public ReplyVo get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public boolean modify(ReplyVo vo) {
		return mapper.update(vo) > 0;
	}

	@Override
	@Transactional
	public boolean remove(Long rno) {
		boardMapper.updateReplyCnt(get(rno).getBno(), -1);
		return mapper.delete(rno)>0;
	}

	@Override
	public List<ReplyVo> getList(ReplyCriteria cri, Long bno) {
		return mapper.getList(bno, cri);
	}
	
	
	
}
