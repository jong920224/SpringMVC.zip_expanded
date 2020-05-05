package kr.co.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.spring.vo.BoardVo;

@Service("boardService")
public interface BoardService {
	
	// 게시판 전체를 조회한다. (READ)
	List<Map<String, Object>> selectBoardList();

	// 게시판 상세페이지를 조회한다. (READ)
	Map<String, Object> selectBoardDetail(Map<String, Object> mapParam);
	
	// 게시글을 등록한다. (CREATE)
	int selectWriteAction(BoardVo boardVo, MultipartFile file);
	
	// 게시글을 삭제한다. (DELETE)
	int selectDeleteAction(Map<String, Object> mapParam);

	// 게시판을 수정한다. (UPDATE)
	int selectUpdateAction(Map<String, Object> mapParam);
	
	// 게시글 전체 카운트를 조회한다.
	int selectBoardCount();
	
	String genSaveFileName(String extName);
	
	boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException;
	
	Map<String, Object> selectFileInfo(String fileNum);

}
