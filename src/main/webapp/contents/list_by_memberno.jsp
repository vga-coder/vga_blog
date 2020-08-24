<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
</head>
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />

  <DIV class='title_line'>
    내가 작성한 글 목록
  </DIV>
  
  <ASIDE style='float: left;'>
    내가 작성한 전체 글 목록입니다.
  </ASIDE>
  <ASIDE style='float: right;'>
<%--     <A href="./create.do?cateno=${cateVO.cateno }">등록</A>
    <span class='top_menu_sep'>&nbsp;</span>   --%>  
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 
  <DIV class='menu_line' style='clear: both;'></DIV>
  
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 15%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 50%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>카테고리</th>
          <th style='text-align: center;'>제목</th>
          <th style='text-align: center;'>회원</th>
          <th style='text-align: center;'>IP</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="cate_Contents_VO" items="${list }">
          <c:set var="cateno" value="${cate_Contents_VO.cateno }" />
          <c:set var="name" value="${cate_Contents_VO.name }" />
          <c:set var="cate_rdate" value="${cate_Contents_VO.cate_rdate }" />          
                    
          <c:set var="contentsno" value="${cate_Contents_VO.contentsno }" />
          <c:set var="title" value="${cate_Contents_VO.title }" />
          <c:set var="web" value="${cate_Contents_VO.web }" />
          <c:set var="ip" value="${cate_Contents_VO.ip }" />
          <c:set var="rdate" value="${cate_Contents_VO.rdate }" />
          
          <c:set var="memberno" value="${memberVO.memberno }" />
          <c:set var="mname" value="${memberVO.mname }" />
          
          <tr> 
            <td style='text-align: center;'>${rdate.substring(0, 10)}</td>
            <td style='text-align: center;'>${name}</td>
            <td>
              <a href="./read.do?contentsno=${contentsno}">${title}</a> 
            </td> 
            <td style='text-align: center;'>${mname}</td>
            <td style='text-align: center;'>${ip}</td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>
 
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html>
    
 