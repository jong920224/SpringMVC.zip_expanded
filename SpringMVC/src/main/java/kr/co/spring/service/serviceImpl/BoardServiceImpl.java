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
	
	// ���� ���
	private static final String SAVE_PATH  = "D:/upload";
	
	@Override
	public List<Map<String, Object>> selectBoardList() {
		return sqlSession.selectList("kr.co.spring.board.selectBoardList");
	}
	
	@Override
	public int selectWriteAction(BoardVo boardVo, MultipartFile file) {
		
		try {
			
			// ���Ͻ����� ����
			String fileNum = sqlSession.selectOne("kr.co.spring.board.selectFileNum");
			System.out.println("fileNum : " + fileNum);
			
			/******************
			 * ���� ������ ����
			 ******************/
			boardVo.setFile_num(fileNum);
			
			// �Խ��� ���̺� ����
			sqlSession.insert("kr.co.spring.board.selectWriteAction",  boardVo);
			
		    // MultipartFile
			String orgFileName = file.getOriginalFilename();
			String extName = orgFileName.substring(orgFileName.lastIndexOf("."), orgFileName.length());
			
			// File Size
			Long size = file.getSize();
			
			// �������� ���� �� ���� �̸�
			String saveFileName = genSaveFileName(extName);
			
			System.out.println("originFilename : " + orgFileName);
			System.out.println("extensionName : " + extName);
			System.out.println("size : " + size);
			System.out.println("saveFileName : " + saveFileName);
			
			// ���� 
			Map<String, Object> mapParam = new HashMap<String, Object>();
			
			mapParam.put("FILE_NUM", fileNum);
			mapParam.put("FILE_ORIGINAL_NAME", orgFileName);
			mapParam.put("FILE_SIZE", size);
			mapParam.put("FILE_PATH", SAVE_PATH);
			mapParam.put("USER_ID", boardVo.getBoard_writer());
			
			/*******************************************
			 * ���� ���ε尡 �Ϸ�Ǹ� ���� ���̺� ����
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
	
	// ���� �ð��� �������� ���� �̸� ����
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
	
	// ���� ���ε� �Լ�
	public boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		
		boolean result = false;

		byte[] data = multipartFile.getBytes();
		
		// File ��½�Ʈ��
		// FilePath�� ������ ���Ͽ� ���� ��� ��Ʈ���� ������.
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		
		//  data�� ��� ��Ʈ���� ����ؼ� ��.
		fos.write(data);
		
		// ���ҽ� ��ȯ
		fos.close();
		
		return result;
	}

	@Override
	// ���� ������ ��ȸ�Ѵ�.
	public Map<String, Object> selectFileInfo(String fileNum) {
		return sqlSession.selectOne("kr.co.spring.board.selectFileInfo", fileNum);
	}
}
