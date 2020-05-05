<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="resources/assets/js/smarteditor/js/service/HuskyEZCreator.js"></script>
	
	<title>글쓰기</title>
</head>
<body>
	<div class="container">
		<h1 style="text-align:center;"><strong>SOOLOG</strong></h1>
	   	<form method="post" id="frm" action="/writeAction.do" encType="multipart/form-data">
			<div class="form-group">
				<label for="exampleFormControlInput1">제목</label>
	       		<input type="text" class="form-control" id="exampleFormControlInput1" name="board_title" placeholder="제목을 작성해주세요." required>
	        </div>
			<div class="form-group">
	            <label for="exampleFormControlInput1">작성자</label>
	            <input type="text" class="form-control" id="exampleFormControlInput1" name="board_writer" value="${sessionScope.USER_ID}" readonly>
          	</div>
          	<div class="form-group">
	            <label for="exampleFormControlTextarea1">내용</label>
	            <textarea class="form-control" id="board_context" name="board_context" rows="10"></textarea>
          	</div>
			<div class="input-group mb-3">
		  		<div class="input-group-prepend">
			    	<span class="input-group-text" id="inputGroupFileAddon01">파일 업로드</span>
			  	</div>
			  	<div class="custom-file">
			    	<input type="file" class="custom-file-input" name="file" aria-describedby="inputGroupFileAddon01" onchange = "textName(this);">
			    	<label id="inputGroupFile01" class="custom-file-label" for="inputGroupFile01"></label>
			  </div>
			</div>
        	<button type="button" class="btn btn-info" id="btnInsert">등록하기</button>
	        <button type="button" class="btn btn-secondary" onclick="location.href='/board.do'">목록으로</button>
	    </form>
    </div>
</body>

<script type="text/javascript"> 
	
	var oEditors = [];

	$(document).ready(function()
	{	
		nhn.husky.EZCreator.createInIFrame({
		    oAppRef: oEditors,
		    elPlaceHolder: "board_context",  //textarea ID
		    sSkinURI: "resources/assets/js/smarteditor/SmartEditor2Skin.html",  //skin경로
		    fCreator: "createSEditor2",
		    htParams:{
		    	
		    	bUseToolBar : true,				// 입력창 크기 조절바 사용여부
		    	buseVerticalResizer : true,		// 모드탭 사용여부
		    	bUseModeChanger : true
		    }
		});
	});
	
	$("#btnInsert").click(function (event) {

        //preventDefault 는 기본으로 정의된 이벤트를 작동하지 못하게 하는 메서드이다. submit을 막음
        event.preventDefault();
		
        // board_context 값 대입
        oEditors.getById["board_context"].exec("UPDATE_CONTENTS_FIELD", []);
        
        // Get form
        var form = $('#frm')[0];

	    // Create an FormData object 
        var data = new FormData(form);
	  	console.log(data);
     	
	  	$('#frm').submit();

    });

	
	// 파일 업로드 시 파일 명이 보이도록
	function textName(input)
	{
		$("#inputGroupFile01").html(input.files[0].name)
	}
	
</script>


