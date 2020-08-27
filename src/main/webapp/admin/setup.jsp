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
  .form {
  position: relative;
  z-index: 1;
  max-width: 800px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
}
</style>
</head>

<body>
<jsp:include page="/menu/top.jsp" flush='false' />

  <DIV class="form" style="margin:100px auto; width:80%; text-align:center">
  
  <FORM name='frm' id='frm' method='POST' action='./mypage.jsp' class="form-horizontal">
			<div class="card card1"> <div style="margin:30px auto; font-size:20px;">회원관리</div>
			<button type="button" onclick="location.href='${root}/mem/list.do'">회원관리</button></div>
			<div class="card card1">  <div style="margin:30px auto; font-size:20px;">카테고리관리</div>
			<button type="button" onclick="location.href='${root}/cate/list.do'">카테고리관리</button></div>
			<div class="card card1">  <div style="margin:40px auto; font-size:20px;">글관리</div>
			<button type="button" onclick="location.href='${root}/contents/list_all.do'">글관리</button></div>
			<div class="card card1">  <div style="margin:40px auto; font-size:20px;">첨부파일관리</div>
			<button type="button" onclick="location.href='${root}/attachfile/list.do'">첨부파일관리</button></div>

  </FORM>
  </div>

 <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
</html>