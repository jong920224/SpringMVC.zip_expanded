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
	
	// �α��� �������� �̵��Ѵ�.
	@RequestMapping(value ="/userLogin.do")
	public String selectUserLogin(Map<String, Object> mapData) {
		return "user/userLogin";
	}	
	
	// ȸ������ �������� �̵��Ѵ�.
	@RequestMapping(value ="/userSign.do")
	public String selectUserSign(Map<String, Object> mapData) {
		return "user/userSign";
	}	
	
	// �α����� �Ѵ�.
	@RequestMapping(value = "/loginAction.do")
	public ModelAndView selectLoginAction(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("USER_ID", userId);
		mapParam.put("USER_PWD", userPwd);
		
		Map<String, Object> user = userService.selectUserLogin(mapParam);
		
		// �ش� ������ ������ ������ 
		
		
		if(user != null) {
			
			// ���� ����
			HttpSession session =  request.getSession();
			
			session.setAttribute("USER_ID", user.get("USER_ID"));
			
			// ���� ��ȿ�ð�
			System.out.println("���� : " + session.getAttribute("USER_ID"));
			System.out.println("�⺻ ���� ��ȿ�ð� : " + session.getMaxInactiveInterval());
		} 
		
		ModelAndView mV = new ModelAndView();
		mV.setViewName("redirect:/index.jsp");
		
		return mV;
	}
	
	// �α��� �� ���̵� �ֳ� Ȯ���� �Ѵ�.
	@ResponseBody
	@RequestMapping(value = "/loginCheck.do")
	public String selectLoginCheck(@RequestParam("userId") String userId, @RequestParam("userPwd") String userPwd) {
		
		System.out.println("���̵� üũ");
		
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
	
	// �α׾ƿ��� �Ѵ�.
	@RequestMapping(value = "/logoutAction.do")
	public String selectLogoutAction(HttpSession session) {
		
		// ������ �����Ѵ�.
		session.invalidate();
		
		return "redirect:/index.jsp";
	}
	
	// ȸ�������� �Ѵ�.
	@RequestMapping(value = "/signAction.do", method=RequestMethod.POST)
	public String selectSingAction(UserVo userVo) {
		
		System.out.println(userVo.toString());
		
		int result = userService.selecUserSign(userVo);
		
		if(result>0) {
			System.out.println("���� ����");
		} else {
			System.out.println("���� ����");
		}
		
		return "redirect:/index.jsp";
	}

	// �ߺ�ȸ���� üũ�Ѵ�.
	@ResponseBody
	@RequestMapping(value ="/signCheck.do")
	public String selectSignCheck(@RequestParam("userId") String userId) {
		
		String rtnVal = "";
		
		System.out.println(userId);
		
		// �Ķ���� ��
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("USER_ID", userId);
		
		Map<String, Object> userMap = userService.selectSignCheck(mapParam);
		
		if(userMap != null) {
			System.out.println("����ڰ� ����.");
			rtnVal = "fail";
		} else {
			System.out.println("����ڰ� ����.");
			rtnVal = "success";
		}
		
		return rtnVal;
	}
}
