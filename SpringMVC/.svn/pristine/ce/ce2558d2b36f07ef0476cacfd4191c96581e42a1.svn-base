package kr.co.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.spring.service.UserService;
import kr.co.spring.vo.UserVo;

@Controller
public class UserController {

	@Resource(name = "userService")
	UserService userService;
	
	// 로그인 페이지로 이동한다.
	@RequestMapping(value ="/userLogin.do")
	public String selectUserLogin(Map<String, Object> mapData) {
		return "user/userLogin";
	}	
	
	// 회원가입 페이지로 이동한다.
	@RequestMapping(value ="/userSign.do")
	public String selectUserSign(Map<String, Object> mapData) {
		return "user/userSign";
	}	
	
	// 로그인을 한다.
	@RequestMapping(value = "/loginAction.do")
	public ModelAndView selectLoginAction(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("USER_ID", userId);
		mapParam.put("USER_PWD", userPwd);
		
		Map<String, Object> user = userService.selectUserLogin(mapParam);
		
		// 해당 유저의 정보가 있으면 
		
		
		if(user != null) {
			
			// 세션 생성
			HttpSession session =  request.getSession();
			
			session.setAttribute("USER_ID", user.get("USER_ID"));
			
			// 세션 유효시간
			System.out.println("세션 : " + session.getAttribute("USER_ID"));
			System.out.println("기본 세션 유효시간 : " + session.getMaxInactiveInterval());
		} 
		
		ModelAndView mV = new ModelAndView();
		mV.setViewName("redirect:/index.jsp");
		
		return mV;
	}
	
	// 로그인 전 아이디가 있나 확인을 한다.
	@ResponseBody
	@RequestMapping(value = "/loginCheck.do")
	public String selectLoginCheck(@RequestParam("userId") String userId, @RequestParam("userPwd") String userPwd) {
		
		System.out.println("아이디 체크");
		
		Map<String, Object> mapParam = new HashMap<String, Object>();
		
		mapParam.put("USER_ID", userId);
		mapParam.put("USER_PWD", userPwd);
		
		Map<String, Object> rtnMap = userService.selectLoginCheck(mapParam);
		
		if(rtnMap != null) {
			System.out.println("success");
			return "success";
		} else {
			System.out.println("fail");
			return "fail";
		}
		
	}
	
	// 로그아웃을 한다.
	@RequestMapping(value = "/logoutAction.do")
	public String selectLogoutAction(HttpSession session) {
		
		// 세션을 삭제한다.
		session.invalidate();
		
		return "redirect:/index.jsp";
	}
	
	// 회원가입을 한다.
	@RequestMapping(value = "/signAction.do", method=RequestMethod.POST)
	public String selectSingAction(UserVo userVo) {
		
		System.out.println(userVo.toString());
		
		int result = userService.selecUserSign(userVo);
		
		if(result>0) {
			System.out.println("가입 성공");
		} else {
			System.out.println("가입 실패");
		}
		
		return "redirect:/index.jsp";
	}

	// 중복회원을 체크한다.
	@ResponseBody
	@RequestMapping(value ="/signCheck.do")
	public String selectSignCheck(@RequestParam("userId") String userId) {
		
		String rtnVal = "";
		
		System.out.println(userId);
		
		// 파라미터 맵
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("USER_ID", userId);
		
		Map<String, Object> userMap = userService.selectSignCheck(mapParam);
		
		if(userMap != null) {
			System.out.println("사용자가 있음.");
			rtnVal = "fail";
		} else {
			System.out.println("사용자가 없음.");
			rtnVal = "success";
		}
		
		return rtnVal;
	}
}
