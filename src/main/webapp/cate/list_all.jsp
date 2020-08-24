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
 
  <DIV class='title_line'>
    <A href="../categrp/list.do">카테고리 그룹</A> > 
    <A href="./list.do">모든 카테고리</A>
  </DIV>
 
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' 
                action='./create.do'>
      <!-- <input type='hidden' name='lang' id='lang' value='en'> --> <!-- ko, en -->

      <label>그룹 번호</label>
      <input type='number' name='categrpno' value='' required="required" 
                min="1" max="99999" step="1" style='width: 5%;'>
         
      <label>카테고리</label>
      <input type='text' name='name' value='' required="required" style='width: 25%;'>
 
      <label>순서</label>
      <input type='number' name='seqno' value='1' required="required" 
                min='1' max='1000' step='1' style='width: 5%;'>
  
      <label>형식</label>
      <select name='visible'>
        <option value='Y' selected="selected">Y</option>
        <option value='N'>N</option>
      </select>
       
      <button type="submit" id='submit'>등록</button>
      <button type="button" onclick="cancel();">취소</button>
    </FORM>
  </DIV>
 
  
<TABLE class='table table-striped'>
  <colgroup>
    <col style='width: 5%;'/>
    <col style="width: 10%;"/>
    <col style='width: 30%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 5%;'/>        
    <col style='width: 20%;'/>
  </colgroup>
 
  <thead>  
  <TR>
    <TH class="th_bs">그룹</TH>
    <TH class="th_bs">순서</TH>
    <TH class="th_bs">카테고리</TH>
    <TH class="th_bs">등록일</TH>
    <TH class="th_bs">출력</TH>
    <TH class="th_bs">글수</TH>
    <TH class="th_bs">기타</TH>
  </TR>
  </thead>
  
  <tbody>
  <c:forEach var="categrp_Cate_VO" items="${list }">  <!-- request 객체에 접근 -->
    <c:set var="categrp_name" value="${categrp_Cate_VO.categrpVO.name}" />
    <c:set var="seqno" value="${categrp_Cate_VO.cateVO.seqno}" />
    <c:set var="cateno" value="${categrp_Cate_VO.cateVO.cateno}" />
    <c:set var="name" value="${categrp_Cate_VO.cateVO.name}" />
    <c:set var="rdate" value="${categrp_Cate_VO.cateVO.rdate}" />
    <c:set var="visible" value="${categrp_Cate_VO.cateVO.visible}" />
    
    <TR>
      <TD class="td_bs">${categrp_name }</TD>
      <TD class="td_bs">${seqno }</TD>
      <TD class="td_bs_left"><A href="./read_update.do?cateno=${cateno }">${name }</A></TD>
      <TD class="td_bs">${rdate.substring(0, 10) }</TD>
      <TD class="td_bs">
        <c:choose>
          <c:when test="${visible == 'Y'}">
            <A href="./update_visible.do?cateno=${cateno }&visible=${visible }"><IMG src="./images/open.png"></A>
          </c:when>
          <c:otherwise>
            <A href="./update_visible.do?cateno=${cateno }&visible=${visible }"><IMG src="./images/close.png"></A>
          </c:otherwise>
        </c:choose>
      </TD>
      <TD class="td_bs">${cnt }</TD>
      <TD class="td_bs">
        <A href="./read_update.do?cateno=${cateno }"><span class="glyphicon glyphicon-pencil"></span></A>
        <A href="./read_delete.do?cateno=${cateno }"><span class="glyphicon glyphicon-trash"></span></A>
        <A href="./update_seqno_up.do?cateno=${cateno }"><span class="glyphicon glyphicon-arrow-up"></span></A>
        <A href="./update_seqno_down.do?cateno=${cateno }"><span class="glyphicon glyphicon-arrow-down"></span></A>         
      </TD>             
    </TR>
  </c:forEach>
  </tbody>
 
</TABLE>
 
 
<jsp:include page="/menu/bottom.jsp" />
</body>
 
</html> 
 
 