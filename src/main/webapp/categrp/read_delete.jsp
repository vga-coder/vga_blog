<%@ page contentType="text/html; charset=UTF-8" %>
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
    
<script type="text/javascript">
 
  
</script>
 
</head> 
 
<body>
<jsp:include page="/menu/top.jsp" />
 
  <DIV class='title_line'>카테고리 그룹 > 삭제</DIV>
 
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <div class="msg_warning">카테고리 그룹을 삭제하면 복구 할 수 없습니다.</div>
    <FORM name='frm_create' id='frm_create' method='POST' 
                action='./delete.do'>
      <input type='hidden' name='categrpno' id='categrpno' value='${categrpVO.categrpno }'>
        
      <label>그룹 이름</label>: ${categrpVO.name } &nbsp;
      <label>순서</label>: ${categrpVO.seqno } &nbsp; 
      <label>출력 형식</label>: ${categrpVO.visible} &nbsp;
       
      <button type="submit" id='submit'>삭제</button>
      <button type="button" onclick="location.href='./list.do'">삭제 취소</button>
    </FORM>
  </DIV>
 
  
<TABLE class='table table-striped'>
  <colgroup>
    <col style="width: 10%;"/>
    <col style='width: 40%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>    
    <col style='width: 20%;'/>
  </colgroup>
 
  <thead>  
  <TR>
    <TH class="th_bs">순서</TH>
    <TH class="th_bs">대분류명</TH>
    <TH class="th_bs">등록일</TH>
    <TH class="th_bs">출력</TH>
    <TH class="th_bs">기타</TH>
  </TR>
  </thead>
  
  <tbody>
  <c:forEach var="categrpVO" items="${list }">
    <c:set var="categrpno" value="${categrpVO.categrpno}" />
    <TR>
      <TD class="td_bs">${categrpVO.seqno }</TD>
      <TD class="td_bs_left"><A href="./read_update.do?categrpno=${categrpno }">${categrpVO.name }</A></TD>
      <TD class="td_bs">${categrpVO.rdate.substring(0, 10) }</TD>
      <TD class="td_bs">${categrpVO.visible }</TD>
      <TD class="td_bs">
        <A href="./read_update.do?categrpno=${categrpno }"><span class="glyphicon glyphicon-pencil"></span></A>
        <A href="./read_delete.do?categrpno=${categrpno }"><span class="glyphicon glyphicon-trash"></span></A>
        <A href="./update_seqno_up.do?categrpno=${categrpno }"><span class="glyphicon glyphicon-arrow-up"></span></A>
        <A href="./update_seqno_down.do?categrpno=${categrpno }"><span class="glyphicon glyphicon-arrow-down"></span></span></A>         
      </TD>         
    </TR>
  </c:forEach>
  </tbody>
 
</TABLE>
 
 
<jsp:include page="/menu/bottom.jsp" />
</body>
 
</html> 
 
 