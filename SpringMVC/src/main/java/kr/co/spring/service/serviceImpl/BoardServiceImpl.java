package kr.co.spring.service.serviceImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.spring.service.BoardService;
import kr.co.spring.vo.BoardVo;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	// 절대 경로
	private static final String SAVE_PATH  = "D:/upload";
	
	@Override
	public List<Map<String, Object>> selectBoardList() {
		return sqlSession.selectList("kr.co.spring.board.selectBoardList");
	}
	
	@Override
	public int selectWriteAction(BoardVo boardVo, MultipartFile file) {
		
		try {
			
			// 파일시퀀스 생성
			String fileNum = sqlSession.selectOne("kr.co.spring.board.selectFileNum");
			System.out.println("fileNum : " + fileNum);
			
			/******************
			 * 파일 시퀀스 저장
			 ******************/
			boardVo.setFile_num(fileNum);
			
			// 게시판 테이블 저장
			sqlSession.insert("kr.co.spring.board.selectWriteAction",  boardVo);
			
		    // MultipartFile
			String orgFileName = file.getOriginalFilename();
			String extName = orgFileName.substring(orgFileName.lastIndexOf("."), orgFileName.length());
			
			// File Size
			Long size = file.getSize();
			
			// 서버에서 저장 할 파일 이름
			String saveFileName = genSaveFileName(extName);
			
			System.out.println("originFilename : " + orgFileName);
			System.out.println("extensionName : " + extName);
			System.out.println("size : " + size);
			System.out.println("saveFileName : " + saveFileName);
			
			// 파일 
			Map<String, Object> mapParam = new HashMap<String, Object>();
			
			mapParam.put("FILE_NUM", fileNum);
			mapParam.put("FILE_ORIGINAL_NAME", orgFileName);
			mapParam.put("FILE_SIZE", size);
			mapParam.put("FILE_PATH", SAVE_PATH);
			mapParam.put("USER_ID", boardVo.getBoard_writer());
			
			/*******************************************
			 * 파일 업로드가 완료되면 파일 테이블에 저장
			 *******************************************/
			if(!writeFile(file, saveFileName)) {
				sqlSession.insert("kr.co.spring.board.selectWriteFile",  mapParam);
			}
		
		}
		catch (IOException  e) {
			throw new RuntimeException(e);
		}
		
		return 1;
	}

	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> mapParam) {
		return sqlSession.selectOne("kr.co.spring.board.selectBoardDetail", mapParam);
	}

	@Override
	public int selectDeleteAction(Map<String, Object> mapParam) {
		return sqlSession.delete("kr.co.spring.board.selectDeleteAction", mapParam);
	}

	@Override
	public int selectBoardCount() {
		return sqlSession.selectOne("kr.co.spring.board.selectBoardCount");
	}

	@Override
	public int selectUpdateAction(Map<String, Object> mapParam) {
		return sqlSession.update("kr.co.spring.board.selectUpdateAction", mapParam);
	}
	
	// 현재 시간을 기준으로 파일 이름 생성
	public String genSaveFileName(String extName) {
	
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += extName;
		
		return fileName;
	}
	
	// 파일 업로드 함수
	public boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		
		boolean result = false;

		byte[] data = multipartFile.getBytes();
		
		// File 출력스트림
		// FilePath로 지정한 파일에 대한 출력 스트림을 생성함.
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		
		//  data를 출력 스트림을 사용해서 씀.
		fos.write(data);
		
		// 리소스 반환
		fos.close();
		
		return result;
	}

	@Override
	// 파일 정보를 조회한다.
	public Map<String, Object> selectFileInfo(String fileNum) {
		return sqlSession.selectOne("kr.co.spring.board.selectFileInfo", fileNum);
	}
}
