<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Login</title>

  <!-- Custom fonts for this template-->
  <link href="resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="resources/css/sb-admin-2.min.css" rel="stylesheet">

</head>
<body class="bg-gradient-primary">

	<div class="container">
    	<!-- Outer Row -->
    	<div class="row justify-content-center">
      		<div class="col-xl-10 col-lg-12 col-md-9">
        		<div class="card o-hidden border-0 shadow-lg my-5">
          			<div class="card-body p-0">
            			<!-- Nested Row within Card Body -->
            			<div class="row">
              				<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
              					<div class="col-lg-6">
                					<div class="p-5">
                  						<div class="text-center">
                    						<h1 class="h4 text-gray-900 mb-4"></h1>
                  						</div>
                					 	
                					 	<form onsubmit="return check();" action="/loginAction.do" method="post" class="user">
                					 		<!-- 아이디 -->
                    						<div class="form-group">
                      							<input type="text" class="form-control form-control-user" id="userId" name="userId">
                   		 					</div>
                   		 					<!-- 패스워드 -->
                    						<div class="form-group">
                      							<input type="password" class="form-control form-control-user" id="userPwd" name="userPwd">
                    						</div>
                    						<div class="form-group">
                      							<div class="custom-control custom-checkbox small">
                        							<input type="checkbox" class="custom-control-input" id="customCheck">
                        							<label class="custom-control-label" for="customCheck">Remember Me</label>
                      							</div>
                    						</div>
                    
                   							<button type="submit" class="btn btn-primary btn-user btn-block"><strong>로그인</strong></button>
                    						<hr>
                    						
                    						<a href="index.html" class="btn btn-google btn-user btn-block">
                      							<i class="fab fa-google fa-fw"></i> 구글 로그인
                    						</a>
                    						<a href="index.html" class="btn btn-facebook btn-user btn-block">
                      							<i class="fab fa-facebook-f fa-fw"></i> 페이스북 로그인
                    						</a>
                  						</form>
                  						
                  						<hr>
                  						<div class="text-center">
                    						<a class="small" href="forgot-password.html">비밀번호 찾기</a>
                  						</div>
                  						<div class="text-center">
                   						 	<a class="small" href="register.html">회원가입</a>
                  						</div>
                					</div>
              					</div>
            				</div>
          				</div>
       				</div>
    			</div>
   			</div>
  		</div>


<script>
	
	// 아이디, 비밀번호 입력 체크
	function check()
	{
		var id = $('#userId').val();
		var pwd = $('#userPwd').val();
		
		var rtn = false;
		
		if(id == "" || pwd == "") 
		{
			alert("아이디를 또는 패스워드를 입력하세요.");
			return false;
		} 
		else 
		{
			
			// 에이작스
			$.ajax({
				
				url : "/loginCheck.do",
				data : { userId:$('#userId').val(), userPwd:$('#userPwd').val() },
				type : "POST",
				async : false,							// 동기식 처리
				dataType : "TEXT",
				success : function(data)
				{
					if(data == "success") {
						rtn = true;
					} else {
						alert('가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.');
						rtn = false;
					}
				},
				error : function() {
				}
				
			});
			
			return rtn;
			
		}
	}
	
</script>

<!-- Bootstrap core JavaScript-->
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="resources/js/sb-admin-2.min.js"></script>

</body>
</html>
