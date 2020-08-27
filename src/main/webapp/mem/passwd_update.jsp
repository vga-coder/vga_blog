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
    if ($('#new_passwd').val() != $('#new_passwd2').val()) {
      msg = '입력된 패스워드가 일치하지 않습니다.<br>';
      msg += "패스워드를 다시 입력해주세요.<br>"; 
      
      alert('입력된 패스워드가 일치하지 않습니다.');
      
      return false; // submit 중지
    }
    alert('패스워드가 변경되었습니다.');
    $('#frm').submit();
  }
  
</script>


</head> 
 
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />

<div class="form" style="margin:100px auto; width:80%; text-align:center">
	<div style="font-size:20px;" class='title_line'>비밀번호 변경</div><br>
			안전한 비밀번호로 내정보를 보호하세요.<br>
			<b>다른 아이디/사이트에서 사용한 적 없는</b> 비밀번호<br>
			<b>이전에 사용한 적 없는</b> 비밀번호가 안전합니다.<br><br><br>

   
<FORM class="login-form" name='frm' id='frm' method='POST' action='./passwd_update.do' class="form-horizontal">
    <input type='hidden' name='memno' id='memno' value='${param.memno }'>       

    <div>
        <input type='password' name='current_passwd' id='current_passwd' value='' required="required" placeholder="현재 패스워드">
        <br><br>
        <input type='password' name='new_passwd' id='new_passwd' value='' required="required" placeholder="새로운 패스워드">
        <input type='password' name='new_passwd2' id='new_passwd2' value='' required="required" placeholder="새로운 패스워드 확인">
        <br><br>
        <button type="button" id='btn_send' >변경</button><br><br>
        <button type="button" onclick="location.href='./mypage.do?memno=${memno}'">취소</button>
    </div>
</FORM>
</div> 
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>
