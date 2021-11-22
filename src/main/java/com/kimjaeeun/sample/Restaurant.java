package com.kimjaeeun.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
//스프링에게 해당 클래스가 스프링에서 관리해야 하는 대상임을 표시
@Component
//클래스에 존재하는 모든 필드에 대한 생성자를 자동으로 생성
@AllArgsConstructor
//파라미터가 없는 생성자를 생성
@NoArgsConstructor
public class Restaurant {// >>인스턴스 생성
	//해당 인스턴스 변수가 스프링으로부터 자동으로 주입해달라는 표시
	@Autowired
	private Chef chef;//포함 >>인스턴스 생성
}
