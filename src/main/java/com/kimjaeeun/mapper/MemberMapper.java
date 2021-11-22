package com.kimjaeeun.mapper;

import java.util.List;
import java.util.Map;

import com.kimjaeeun.domain.AuthVo;
import com.kimjaeeun.domain.MemberVo;

public interface MemberMapper {
	void insertMember(Map<String,Object>map);
	void insertAuth(Map<String,Object>map);
	MemberVo read(String userid);
}
