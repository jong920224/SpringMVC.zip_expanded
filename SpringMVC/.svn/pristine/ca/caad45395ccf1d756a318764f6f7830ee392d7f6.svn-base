package kr.co.spring.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.spring.vo.UserVo;

@Service("userService")
public interface UserService {
	
	// 로그인을 진행한다.
	Map<String, Object> selectUserLogin(Map<String, Object> mapParam);
	
	// 회원가입을 진행한다.
	int selecUserSign(UserVo userVo);
	
	// 로그인 전 사용자가 있는지를 체크한다.
	Map<String, Object> selectLoginCheck(Map<String, Object> mapParam);
	
	// 회원가입 전 사용자가 있는지를 체크한다.
	Map<String, Object> selectSignCheck(Map<String, Object> mapParam);

}
