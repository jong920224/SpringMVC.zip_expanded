<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<script src="https://code.jquery.com/jquery-2.2.1.js"></script>
	<script type="text/javascript" src="resources/assets/js/smarteditor/js/service/HuskyEZCreator.js"></script>
	<title>상세화면</title>
</head>
<body>
	<h1 style="text-align:center;"><strong><a href="/index.jsp">SOOLOG</a></strong></h1>
	<hr>
	
	<div class="container">
	   	<form onsubmit="return befoUpdate();" action="/updateAction.do" method="post">
	   		<table class="table">
	   			<tr>
	   				<th>번호</th>
	   				<td>${boardDetail.BOARD_NUM}</td>
	   				<th>조회수</th>
	   				<td>${boardDetail.READ_COUNT}</td>
	   			</tr>
	   			
	   			<tr>
	   				<th>작성자</th>
	   				<td>${boardDetail.BOARD_WRITER}</td>
	   				<th>작성일</th>
	   				<td>${boardDetail.REG_DATE}</td>
	   			</tr>
	   			
	   			<tr>
	   				<th>제목</th>
	   				<td colspan="3"><input type="text" class="form-control" id="BOARD_TITLE" name="BOARD_TITLE" value='${boardDetail.BOARD_TITLE}' style="width:100%;" readonly></td>
	   			</tr>
	   			
	   			<tr>
	   				<td colspan="4">
	   			  	<div class="form-group">
		            	<textarea class="form-control" id="BOARD_CONTEXT" name="BOARD_CONTEXT" rows="10" readonly>${boardDetail.BOARD_CONTEXT}</textarea>
		          	</div>
		          	</td>
	   			</tr>
	   			<tr>
	   				<td colspan="4">
	 					<div class="input-group mb-3">
				  			<div class="input-group-prepend">
					    		<span class="input-group-text" id="inputGroupFileAddon01">파일</span>
					  		</div>
					  		<div class="custom-file">
						    	<a href="javascript:fileDown()" type="text" class="form-control" aria-describedby="inputGroupFileAddon01">${boardDetail.FILE_NAME}</a>
						    	<input type="hidden" name="FILE_NUM" value='${boardDetail.FILE_NUM}'>
						  	</div>
						</div>
					</td>
	   			</tr>
	        	<tr>	        	
		        	<td colspan="4" class="text-right">
		        		<button type="submit" class="btn btn-secondary" id="btnUpd">수정하기</button>
		        		<button type="button" class="btn btn-secondary" id="btnDel" onclick="befoDelete();">삭제하기</button>
		        		<button type="button" class="btn btn-secondary" onclick="location.href='/board.do'">목록으로</button>
		        	</td>
	        	</tr>
	        </table>
	        <input type="hidden" class="form-control" id="BOARD_NUM" name="BOARD_NUM" value='${boardDetail.BOARD_NUM}'>
	    </form>
    </div>
</body>
</html>

<script>
	
	// 텍스트 편집기에서 받아온 데이터를 담는 배열
	var oEditors = [];
	
	$(document).ready(function()
	{
		// 스마트 에디터
		nhn.husky.EZCreator.createInIFrame({
		    oAppRef: oEditors,
		    elPlaceHolder: "BOARD_CONTEXT",  //textarea ID
		    sSkinURI: "resources/assets/js/smarteditor/SmartEditor2Skin.html",  //skin경로
		    fCreator: "createSEditor2",
		    htParams:{
		    	
		    	bUseToolBar : true,				// 입력창 크기 조절바 사용여부
		    	buseVerticalResizer : true,		// 모드탭 사용여부
		    	bUseModeChanger : true
		    }
		});
		
		if(${sessionScope.USER_ID eq boardDetail.BOARD_WRITER})
		{
			$("#BOARD_TITLE").attr("readonly",false);
			$("#BOARD_CONTEXT").attr("readonly",false);
			$("#btnUpd").attr("disabled",false);
			$("#btnDel").attr("disabled",false);
		} 
		else 
		{
			$("#btnUpd").attr("disabled",true);
			$("#btnDel").attr("disabled",true);
		}
	});
	
	function fileDown() 
	{
		console.log(${boardDetail.FILE_NUM});
		location.href='/selectFileDown.do?FILE_NUM=' + '${boardDetail.FILE_NUM}';
	}
	
	// 삭제 전 
	function befoDelete()
	{
		var yn = confirm("정막 삭제하시겠습니까?");
		
		if(yn) {
			location.href='/deleteAction.do?boardNum=' + ${boardDetail.BOARD_NUM};
		} else {
			return false;
		}
	} 
	
	// 수정 전
	function befoUpdate()
	{
		var yn = confirm("수정하시겠습니까?");
		
		if(yn) {
			oEditors.getById["BOARD_CONTEXT"].exec("UPDATE_CONTENTS_FIELD", []); 
			return true;
		} else {
			return false;
		}
	}
	
</script>

