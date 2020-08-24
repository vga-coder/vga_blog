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
  function loadDefault() {
    $('#id_admin').val('admin1');
    $('#passwd_admin').val('1234');
  }
  </script> 


</head> 

<body>
  <jsp:include page="/menu/top.jsp" flush='false' />

  <div class="form" style="margin:100px auto; width:80%; text-align:center">


  <DIV style="font-size:20px;" class='title_line'>
  관리자 로그인
  </DIV>
  <br>
  
  <FORM class="login-form" name='frm' id='frm' method='POST' action='./login.do' class="form-horizontal">

  <div>
      <input class="a" type="text" name='id_admin' id='id_admin' value='${ck_id }' required="required" placeholder="관리자 아이디"/>

  </div>
  <div>
      <input class="a" type="password" name='passwd_admin' id='passwd_admin' value='${ck_passwd }' required="required" placeholder="비밀번호"/>

  </div>
  <div>
      <button type="submit" >로그인</button>
      <button type='button' onclick="loadDefault();">테스트 계정</button>
  </div>
<br>

  </FORM>
</div>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>


