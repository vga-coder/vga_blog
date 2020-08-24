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

<style>
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #ffb6c1;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form button:hover,.form button:active,.form button:focus {
  background: #80c1e1;
}
</style>
</head>

<body>
  <jsp:include page="/menu/top.jsp" flush='false' />

  <div class="form" style="margin:100px auto; width:80%; text-align:center">


  <DIV style="font-size:20px;" class='title_line'>
  vgaBlog
  </DIV>
  <br>
  
  <FORM class="login-form" name='frm' id='frm' method='POST' action='./login.do' class="form-horizontal">

  <div>
      <button type="button" onclick="location.href='${root}/admin/login.do'">관리자 로그인</button>
      <BR><BR>
      <button type="button" onclick="location.href='${root}/mem/login.do'">회원 로그인</button>
      <BR><BR>
      <p class="message">Not registered? <a href="${root}/mem/create.do">회원가입</a></p>
  </div>

  </FORM>
</div>  
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>


