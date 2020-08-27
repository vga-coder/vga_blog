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
    /* 테이블의 테두리 영역을 확인하기 위해 사용하는 소스입니다. */
    
    table.tbl_sty{
    border: 1px solid gray;
    border-collapse: collapse;
    }
    table.tbl_sty tr{
    border: 1px solid gray;
    }
    table.tbl_sty th{
    border: 1px solid gray;
    padding: 20px;
    }
    table.tbl_sty td{
    border: 1px solid gray;
    padding: 20px;
    } 
  </style>

<script type="text/javascript">

</script>
</head> 

<body>
  <jsp:include page="/menu/top.jsp" flush='false' />

  <div style="margin:100px auto; width:80%; text-align:center">


  <DIV style="margin:20px auto; font-size:20px;" class='title_line'>
    회원 목록
  </DIV>
  
  <div class='menu_line'></div>
  
  <FORM name='frm' id='frm' method='POST' action='./create.do' class="form-horizontal">

 <TABLE class='table table-striped'>
  <colgroup>
    <col style="width: 20%;"/>
    <col style='width: 20%;'/>
    <col style='width: 30%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
  </colgroup>
 
  <thead>  
  <TR>
    <TH class="th_bs">아이디</TH>
    <TH class="th_bs">이름</TH>
    <TH class="th_bs">생년월일</TH>
    <TH class="th_bs">가입날짜</TH>
    <TH class="th_bs">관리</TH>
  </TR>
  </thead>
  
  <tbody>
  <c:forEach var="memVO" items="${list }">  <!-- request 객체에 접근 -->
    <c:set var="memno" value="${memVO.memno}" />
    <TR>
      <TD>${memVO.mem_id }</TD>
      <TD>${memVO.mem_name }</TD>
      <TD>${memVO.mem_yy}년/${memVO.mem_mm}월/${memVO.mem_dd}일</TD>
      <TD>${memVO.signdate}</TD>
      <TD><A href="./delete.do?memno=${memno}">삭제</A></TD>
    </TR>
  </c:forEach>
  </tbody>
 
</TABLE>
  </FORM>
</div>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>


