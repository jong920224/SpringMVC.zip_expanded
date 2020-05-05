package kr.co.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.spring.service.BoardService;
import kr.co.spring.vo.BoardVo;

@Controller
public class BoardController {
	
	@Resource(name ="boardService")
	BoardService boardService;
	
	// 전체 게시판을 조회한다.
	@RequestMapping(value = "/board.do", method = RequestMethod.GET)
	public ModelAndView selectBoard() 
	{
		
		// 게시글 전체 수(14)
		int totalCount = boardService.selectBoardCount();
		
		// 한 페이지 게시글
		int listCount = 5;
		
		// 현재 보고있는 페이지
		int page = 1;
		
		int totalPage = totalCount - listCount;
		
		if (totalCount % listCount > 0) {
			totalPage++;
		}
			
		//page = 현재 보고있는 페이지
		if (totalPage < page){
		    page = totalPage;
		}
		
		List<Map<String, Object>> boardList = boardService.selectBoardList();
		
		ModelAndView mV = new ModelAndView();
		
		mV.setViewName("board/board");
		mV.addObject("boardList", boardList);
		
		return mV;
	}
	
	// 글쓰기 페이지로 이동한다.
	@RequestMapping(value ="/boardWrite.do")
	public String selectBoardWrite(Map<String, Object> mapData) {
		return "board/boardWrite";
	}
	
	// 글을 등록한다.
	@RequestMapping(value ="/writeAction.do", method = RequestMethod.POST)
	public String selectWriteAction(BoardVo boardVo, @RequestParam("file") MultipartFile file) {
		
		String match = "<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>";
		  
		String context = boardVo.getBoard_context();
		
		context = context.replaceAll(match, "");
		boardVo.setBoard_context(context);
		
		int result = boardService.selectWriteAction(boardVo, file);
		
		if(result>0) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 오류");
		}
		
		return "redirect:/board.do";
	}	
	
	// 상세화면 페이지로 이동한다.
	@RequestMapping(value ="/boardDetail.do")
	public ModelAndView selectBoardDetail(@RequestParam("boardNum") int boardNum) {
		System.out.println("dd");
		// Controller의 처리결과를 보여줄 view와 view에서 사용할 값을 전달하는 ModelAndView 선언
		ModelAndView mV = new ModelAndView();
		
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("BOARD_NUM", boardNum);
		
		Map<String, Object> rtnMap = boardService.selectBoardDetail(mapParam);
		
		System.out.println(rtnMap.toString());
		
		mV.setViewName("board/boardDetail");
		mV.addObject("boardDetail", rtnMap);
		
		return mV;
	}
	
	// 글을 삭제한다.
	@RequestMapping(value ="/deleteAction.do")
	public String selectDeleteAction(@RequestParam("boardNum") int boardNum) {
		
		// 파라미터를 담을 맵 선언
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("BOARD_NUM", boardNum);
		
		// 삭제 로직
		int rtnVal = boardService.selectDeleteAction(mapParam);	
		
		if(rtnVal>0) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 오류");
		}
		
		return "redirect:/board.do";
	}
	
	// 글을 수정한다.
	@RequestMapping(value = "/updateAction.do")
	public ModelAndView selectUpdateAction(HttpServletRequest request) {
		
		System.out.println(request.getParameter("BOARD_NUM"));
		
		int boardNum = Integer.parseInt(request.getParameter("BOARD_NUM"));
		String boardTitle = request.getParameter("BOARD_TITLE");
		String boardContext = request.getParameter("BOARD_CONTEXT");
		
		// html 태그를 없애준다.
		String match = "<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>";
		boardContext = boardContext.replaceAll(match, "");
		
		// 파라미터 맵 선언
		Map<String, Object> mapParam = new HashMap<String, Object>();
		
		mapParam.put("BOARD_NUM", boardNum);
		mapParam.put("BOARD_TITLE", boardTitle);
		mapParam.put("BOARD_CONTEXT", boardContext);
		
		int result = boardService.selectUpdateAction(mapParam);
		
		if(result>0)
		{
			System.out.println("업데이트 성공");
		} else {
			System.out.println("업데이트 실패");
		}
		
		ModelAndView mV = new ModelAndView();
		Map<String, Object> rtnMap = boardService.selectBoardDetail(mapParam);
		
		mV.addObject("boardDetail", rtnMap); 
		mV.setViewName("board/boardDetail");
		
		return mV;
	}
	
	@RequestMapping(value = "/selectFileDown.do")
	public void selectFileDown(@RequestParam("FILE_NUM") String FileNum, HttpServletRequest request, 
								HttpServletResponse response) {
		
		// 다운로드 명
		String downName = null;
		
		// Context Root 경로
		String defaultfilepath = request.getSession().getServletContext().getRealPath("/");
		System.out.println(defaultfilepath);
		
		// 파일 정보를 받아옴.
		Map<String, Object> fileMap = boardService.selectFileInfo(FileNum);
		
		// 원본 파일명
		String fileName = (String) fileMap.get("FILE_ORIGINAL_NAME");	
		
		// 파일 경로
		String filePath = (String) fileMap.get("FILE_PATH");			
		
		// 경로와 파일명을 더해줌.
		StringBuffer sb = new StringBuffer(filePath + "/");
		sb.append(fileName);
		
		// 문자열로 변환
		String saveFileName = sb.toString();
		
		// 브라우저 종류를 가져오는것
		String browser = request.getHeader("User-Agent"); 
		
		// 지정된 경로에 파일명으로 객체 생성
		File file = new File(saveFileName);
		
		if (file.exists() && file.isFile()) 
		{
			// 입력스트림
			FileInputStream fileInputStream = null;
			
			// 출력스트림
			ServletOutputStream servletOutputStream = null;
			
			try {
				
				/********************************************
				 ** 파일 인코딩 : 브라우저 확인 파일명 encode  
				 ** 원본 파일명을 인코딩 하여, 다운로드하는 상대방도 원본파일명으로 받을 수 있다.
				 *****************************************************************************************/
			     if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){
			         downName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
			     } else{
			    	 downName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			     }	
		
			     response.setHeader("Content-Disposition","attachment;filename=\"" + downName +"\"");
			     
			     // 디폴트 미디어 타입은 운영체제 종종 실행파일, 다운로드를 의미    
			     response.setContentType("application/octer-stream");
			     response.setHeader("Content-Transfer-Encoding", "binary;");
			     
			     // 서버에 저장된 파일을, 저장된 파일명으로 위에서 객체를 만들었으므로
			     // 아래 코드는 서버에 저장된 파일을 읽는 역할 입니다.
			     fileInputStream = new FileInputStream(file);
			     
			     // servletInputStream : 서버의 파일을 자바로 읽는다.
			     // 파일을 읽어올 때에는 FileInputStream으로 읽어온 뒤
			     // 브라우저에 출력할 때에는 ServletOutputStream을 사용합니다.
			     // 즉, 사용자가 해당 파일을 다운로드 받을 수 있게 출력을 해줘야 합니다.
			     servletOutputStream = response.getOutputStream(); // 서버의 파일을 바깥쪽으로 보낼때
			     
			     byte b [] = new byte[1024];
			     int data = 0;
			     
			     while((data = (fileInputStream.read(b, 0, b.length))) != -1) {
			         servletOutputStream.write(b, 0, data);
			     }
			     
			} catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
				     // 버퍼에 남아있는 데이터 비우기
				     servletOutputStream.flush();
				     
				     fileInputStream.close();
				     servletOutputStream.close();
				} catch(Exception e2) {
					
				}
			}
		     
		}
	}
	
}
