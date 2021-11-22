package com.kimjaeeun.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kimjaeeun.domain.AttachVo;
import com.kimjaeeun.domain.BoardVo;
import com.kimjaeeun.domain.Criteria;
import com.kimjaeeun.domain.PageDTO;
import com.kimjaeeun.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller //spring의 빈으로 인식하게함
@Log4j
@RequestMapping("board/*") //board로 시작하는 모든 처리를 BoardController가 하도록 지정
@AllArgsConstructor //BoardService에 대해서 의존적이므로 생성자를 만들고 자동으로 주입하도록 함
public class BoardController {
	private BoardService service; 
	private UploadController uploadController;
	
	 //페이지번호에 따른 게시글 목록 출력
     //@RequestMapping(method = RequestMethod.GET ...) 
	 @GetMapping("list")
	   //나중에 게시물 목록을 전달해야 하므로 Model을 파라미터로 지정
	   public void list(Model model, Criteria cri){
	      log.info("board.list");
	      //model에 BoardServiceImpl의 결과를 담아 전달
	      model.addAttribute("list", service.getList(cri)); 
	      model.addAttribute("page", new PageDTO(service.getTotal(cri),cri));
	   }
	 @PreAuthorize("isAuthenticated()")
	 @GetMapping("register")
	 public void register(){
		 
	 }
	
	//게시글 작성
	 @PreAuthorize("isAuthenticated()")
	@PostMapping("register")
	//추가적으로 새롭게 등록된 게시물의 번호를 같이 전달하기 위해 RedirectAttributes이용
	public String register(BoardVo boardVo, RedirectAttributes rttr){
		log.info("register :: " + boardVo);
		service.register(boardVo);
		log.info("register :: " + boardVo);
		rttr.addFlashAttribute("result",boardVo.getBno());//result에 bno담아서 데이터 전달
		//redirect: 스프링MVC가 내부적으로response.sendRedirect()를 처리
		return "redirect:/board/list"; //게시글작성 메소드 끝나면 list페이지로 돌아감
	}
	
	//게시글 상세보기
	//상세보기랑 수정버튼 눌렀을떄도 이 메서드 실행
	@GetMapping({"get","modify"})
	//@RequestParam:bno값을 좀 더 명시적으로 처리
	//Model: 화면쪽으로 해당 번호의 게시물을 전달해야함
	//@ModelAttribute
	//1.Criteria클래스의 cri객체를 자동으로 생성(빈클래스여야됨)
	//2.생성된 오브젝트(cri) HTTP로 넘어 온 값들을 자동으로 바인딩
	//3.@ModelAttribute 어노테이션이 붙은 객체가 (Criteria객체) 자동으로 Model객체에 추가되고 뷰단으로 전달
	public void get(@RequestParam("bno") Long bno, Model model, @ModelAttribute("cri") Criteria cri){
		log.info("get");
		model.addAttribute("board",service.get(bno));
	}
	
	//게시글 수정
	@PreAuthorize("principal.username==#boardVo.writer")
	@PostMapping("modify")
	public String modify(BoardVo boardVo, RedirectAttributes rttr,Criteria cri){
		log.info("modify :: " + boardVo);
		if(service.modify(boardVo)){
		rttr.addFlashAttribute("result","success");
		}
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/board/list";
	}
	@PreAuthorize("principal.username==#writer")
	@PostMapping("remove")
	public String remove(@RequestParam("bno") Long bno , RedirectAttributes rttr,Criteria cri,String writer){
		log.info("remove :: " + bno);
		List<AttachVo>list =service.getAttachs(bno);
		if(service.remove(bno)){
			list.forEach(vo->{
				uploadController.deleteFile(vo.getFullPath(), vo.isImage());
			});
			rttr.addFlashAttribute("result","success");
		}
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		return "redirect:/board/list";
	}
	
	@GetMapping("getAttachs/{bno}")
	public @ResponseBody List<AttachVo> getAttachs(@PathVariable Long bno){
		return service.getAttachs(bno);
	}
	
	@GetMapping("editor")
	public void editorForm(){
		log.info("editor form");
	}
	
	@PostMapping("editor")
	public void editor(BoardVo vo) {
		log.info("editor ::" + vo);
		service.register(vo);
	}
}
