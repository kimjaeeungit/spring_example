package com.kimjaeeun.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.Data;

@Data
public class SampleDTOList {
	@Autowired 
	private List<SampleDTO> list;
}
