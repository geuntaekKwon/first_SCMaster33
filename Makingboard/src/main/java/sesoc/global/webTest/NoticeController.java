package sesoc.global.webTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sesoc.global.webTest.dao.NoticeDAORepository;
import sesoc.global.webTest.util.FileService;
import sesoc.global.webTest.util.PageNavigator;
import sesoc.global.webTest.vo.Notice;

@Controller
public class NoticeController {
	
	private final String uploadPath = "/noticefile";
	
	@Autowired
	NoticeDAORepository repo;
	//완
	@RequestMapping("/noticeList")
	public String noticeList(Model model,
			@RequestParam(value="searchtype", defaultValue="title") String searchtype,
			@RequestParam(value="searchword", defaultValue="") String searchword, 
			@RequestParam(value="currentPage", defaultValue="1") int currentPage)
	{
		System.out.println("[ NoticeList ]");
		
		Map<String, String> map = new HashMap<>();
		map.put("searchword", searchword);
		map.put("searchtype", searchtype);
		
		//Paging
		
		int totalRecordCount = repo.getNoticeCount(map);
		PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);
		RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());
		
		//
		List<Notice> list = repo.select(map, rb, navi.getStartRecord(), navi.getCountPerPage());
		
		
		model.addAttribute("navi", navi);
		model.addAttribute("searchtype", searchtype);
		model.addAttribute("searchword", searchword);
		model.addAttribute("list", list);
		
		return "notice/notice";
	}//NoticeList
	//완
	@RequestMapping("/noticeWrite")
	public String noticeWrite(){
		System.out.println("[ noticeWrite ]");
		return "notice/noticeWrite";
	}//NoticeList
	@RequestMapping(value="/noticeWrite", method=RequestMethod.POST)
	public String noticeWrite(Model model, Notice notice, HttpSession session, MultipartFile upload){
		System.out.println("[ noticeWrite ]");

		notice.setCustid((String)session.getAttribute("loginId"));
		if(!upload.isEmpty()){
			notice.setOriginalfile(upload.getOriginalFilename());
			notice.setSavedfile(FileService.saveFile(upload, uploadPath));
		}//if
		
		repo.insert(notice);
		return "redirect:/noticeList";
	}//NoticeList
	
	@RequestMapping("/noticeDetail")
	public String noticeDetail(Model model, int noticenum){
		Notice notice = repo.selectOne(noticenum);
		repo.incrementCount(noticenum);
		model.addAttribute("notice", notice);
		return "notice/noticeDetail";
	}//noticeDetail
	
	//완
	@RequestMapping("/noticeUpdate")
	public String noticeUpdate(Model model, int noticenum){
		System.out.println("테스트 : " + noticenum);
		Notice notice = repo.selectOne(noticenum);
		model.addAttribute("notice", notice);
		return "notice/noticeUpdate";
	}//noticeDetail
	@RequestMapping(value="/noticeUpdate", method=RequestMethod.POST)
	public String noticeUpdate(Model model, Notice notice, MultipartFile fileChange, int noticenum){
		Notice existing_notice = repo.selectOne(noticenum);
		
		if(!fileChange.isEmpty()){
			FileService.deleteFile(uploadPath + "/" +existing_notice.getSavedfile());
			
			notice.setOriginalfile(fileChange.getOriginalFilename());
			notice.setSavedfile(FileService.saveFile(fileChange, uploadPath));
		}//if
		notice.setNoticenum(noticenum);
		repo.update(notice);
		return "redirect:/noticeList";
	}//noticeDetail
	
	@RequestMapping("/noticeDelete")
	public String noticeDelete(Model model, int noticenum){
		repo.delete(noticenum);
		return "redirect:/noticeList";
	}//noticeDetail
	
}//class
