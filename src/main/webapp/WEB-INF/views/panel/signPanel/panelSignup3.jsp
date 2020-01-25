<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/panel/common/head.jsp" %>
</head>
<style>
#titleTable {
	margin: 0 auto;
	margin-top: 20px;
}

#menuTitle {
	height: 30px;
	width: 40%;
	font-size: 16pt;
	text-align: left;
	vertical-align: bottom;
	font-weight: bold;
	color: #3D3D3D;
}

#pagePath {
	width: 60%;
	text-align: right;
	vertical-align: bottom;
	font-weight: bold;
}

#depth3 {
	color: #008499;
}

#depth1, #depth2 {
	color: #494949;
}
#depth1:hover,  #depth2:hover, #depth3:hover {
	cursor: default;
}
#depth1:hover, #depth2:hover {
	color: #494949;
}

.sectionLine {
	padding-bottom: 40px;
}
/* 여기까지 페이지제목 및 경로 영역 */


</style>
<body>
	<div class="wrap">
		<%@ include file="/WEB-INF/views/panel/common/header.jsp" %>
		<section class="container">
		<br />
		
		
			<table id="titleTable" height="10px;" width="99%;">
				<tr>
					<td id="menuTitle">회원가입</td>
					<td id="pagePath">
						<div class="ui breadcrumb">
							<a class="active section" id="depth1">이용약관</a>
							<i class="right angle icon divider"></i>
							<a class="active section" id="depth2">정보입력</a>
							<i class="right angle icon divider"></i>
							<div class="active section" id="depth3">가입완료</div>
						</div>
					</td>
				</tr>
			</table>
			
			<div class="sectionLine">
				<hr>
			</div>
			
			
		
			회원가입 인증 이메일을 발송하였습니다.
			이메일을 확인하여 정회원 인증하신 후에 로그인 하실 수 있습니다.
			
			
		<br />
		</section>  <!-- container end -->
		<%@ include file="/WEB-INF/views/panel/common/footer.jsp" %>
	</div>  <!-- wrap end -->
</body>
</html>















