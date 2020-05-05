<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-2.2.1.js"></script>
	
	<!-- 다음 주소 API -->
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	
	<title>회원가입</title>
</head>
<body>
	<div class="container">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<h1 style="text-align:center;"><strong>SOOLOG</strong></h1>
		   	<form onsubmit="return befoSignCheck();" action="/signAction.do" method="post">
		   		<div class="form-group">
					<label for="exampleFormControlInput1">아이디</label>
		       		<input type="text" class="form-control" id="USER_ID" name="USER_ID" required>
		        </div>
				<div class="form-group">
					<label for="exampleFormControlInput1">비밀번호</label>
		       		<input type="password" class="form-control" id="USER_PWD" name="USER_PWD" required>
		        </div>
     			<div class="form-group">
					<label for="exampleFormControlInput1">비밀번호 재확인</label>
		       		<input type="password" class="form-control" id="USER_PWD_RE" name="USER_PWD_RE" onkeyup="pwdKeyDown()">
		       		<p id="pwdChk" style="color:red; display:none;">비밀번호가 틀립니다.</p>
		        </div>
		   		<div class="form-group">
					<label for="exampleFormControlInput1">이름</label>
		       		<input type="text" class="form-control" id="KNAME" name="KNAME" required>
		        </div>	
		        <div class="form-group">
		        	<label for="exampleFormControlInput1">성별</label>
		        	<select name="GENDER" class="form-control">
		        		<option value ="" selected>성별</option>
				  		<option value="1">남자</option>
					  	<option value="2">여자</option>
					</select>
		        </div>
		   		<div class="form-group">
					<label for="exampleFormControlInput1">주소</label>
		       		<input type="text" class="form-control" id="ADDR1" name="ADDR1">
		       		<input type="text" class="form-control" id="ADDR2" name="ADDR2" style="margin-top : 3px; margin-bottom : 3px;">
		       		<button type="button" onclick="daumAddrFunc();" class="btn btn-info" style="width:100%;">우편번호 찾기</button>
		        </div>			        
		   		<div class="form-group">
					<label for="exampleFormControlInput1">휴대전화</label>
		       		<input type="text" class="form-control" id="PHONE" name="PHONE">
		        </div>			        
		        <hr>
		        <div class="form-group">	        
		        	<button type="submit" class="btn btn-primary" style="width:100%;"><strong>가입하기</strong></button>
		        </div>
		    </form>
	    </div>
    </div>
</body>
<script>
	
	var pwdChk = false;	// 비밀번호 재확인 변수
	var boolChk = false;
	
	// 비밀번호 재확인 함수
	function pwdKeyDown() {
		// 비밀번호가 같으면
		if($("#USER_PWD").val() == $("#USER_PWD_RE").val()) {
			pwdChk = true;
			$("#pwdChk").hide();
		} else {
			pwdChk = false;
			$("#pwdChk").show();
		}
	}

	// 다음 주소 API
	function daumAddrFunc(){
		new daum.Postcode({
		    oncomplete: function(data) 
		    {
	    	   var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
               var extraRoadAddr = ''; // 도로명 조합형 주소 변수
               
               // 법정동명이 있을 경우 추가한다. (법정리는 제외)
               // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
               if (data.bname !== ''
                       && /[동|로|가]$/g.test(data.bname)) {
                   extraRoadAddr += data.bname;
               }
               // 건물명이 있고, 공동주택일 경우 추가한다.
               if (data.buildingName !== ''
                       && data.apartment === 'Y') {
                   extraRoadAddr += (extraRoadAddr !== '' ? ', '
                           + data.buildingName : data.buildingName);
               }
               // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
               if (extraRoadAddr !== '') {
                   extraRoadAddr = ' (' + extraRoadAddr + ')';
               }
               // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
               if (fullRoadAddr !== '') {
                   fullRoadAddr += extraRoadAddr;
               }
	    	   
               document.getElementById('ADDR1').value = fullRoadAddr;
               document.getElementById('ADDR2').value = data.jibunAddress;
		    }
		}).open();
	}
	
	// 서버에 폼을 던지기 전에 체크사항
	function befoSignCheck(){
		
		if(!pwdChk) {
			alert("비밀번호를 확인해주세요.");
			return false;
		} else {
			
			// ajax를 이용하여 현재 입력한 아이디가 존재하는지 체크
			// 동기식 처리를 하는 이유
			// 비동기식으로 처리하게 되면 서버에 전달한 아이디 중복확인 체크 결과를
			// 기다리지 않기 때문에 boolChk 변수에 값을 넣을 수 없음.
			// 따라서 동기식으로 처리를 하여 결과값을 받을 수 있도록 처리.
			$.ajax({
				url : "/signCheck.do",
				data : { userId:$('#USER_ID').val() },
				type : "POST",
				async : false,							// 동기식 처리
				dataType : "TEXT",
				success : function(data)
				{
					if(data == "success") {
						// 가입가능
						boolChk = true;
					} else {
						// 가입불가능
						alert('중복된 아이입니다.');
						boolChk = false;
					}
				},
				error : function() {
				}
			});
			
			return boolChk;
		}
	}
	
</script>
</html>
