<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="root" value="${pageContext.request.contextPath}" /> 

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <title>vgaBlog</title>
  
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="${root }/css/style.css">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
  <script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="${root }/javascript/script.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<script type="text/javascript">
  $(function() { // 자동 실행
    $('#btn_send').on('click', send); 
  });

  function send() {
    alert('사진이 수정되었습니다.');
    $('#frm').submit();
  }
  
</script>

</head> 
 
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />

<div class="form" style="margin:20px auto; width:80%; text-align:center">
	<div style="font-size:20px;" class='title_line'>vgaBlog 프로필</div><br>

 
			

<FORM name='frm' id='frm' method='POST' action='./mem_img_update.do' enctype="multipart/form-data">
    <input type='hidden' name='memno' id='memno' value='${param.memno }'>       

    <div>
        <input type='file' name='mem_imgMF' id='mem_imgMF' 
                    value='' placeholder="파일 선택">
                    
		<b>이름</b> : ${memVO.mem_name } <br>
		<b>생년월일</b> : ${memVO.mem_yy }/${memVO.mem_mm }/${memVO.mem_dd }<br>
        <button type="button" id='btn_send' >변경</button><br><br>
        <button type="button" onclick="location.href='./mypage.do?memno=${memno}'">취소</button>
    </div>
</FORM>
</div> 
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>
