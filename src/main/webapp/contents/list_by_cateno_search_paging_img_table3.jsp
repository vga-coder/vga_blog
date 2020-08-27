<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>vgaBlog</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
</head>
 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<div style="margin:100px auto; width:80%; text-align:center">

  <DIV class='title_line'><br>
   <h1> ${cateVO.name }</h1><br>
  </DIV>

  <form name='frm' id='frm' method='get' action='./list.do'>
    <input type='hidden' name='cateno' value='${cateVO.cateno }'>
      
    <ASIDE style='float: left;'>
      ${categrpVO.name } > ${cateVO.name }
      <c:choose>
        <c:when test="${param.word != ''}"> <%-- 검색하는 경우 --%>
          > '${param.word }' ${search_count } 건 검색됨.
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          > ${search_count } 건 등록됨
        </c:otherwise>
      </c:choose>    
    </ASIDE>
    <ASIDE style='float: right;'>
      <c:if test="${sessionScope.mem_id != null or sessionScope.id_admin != null}">
        <A href="./create.do?cateno=${cateVO.cateno }">등록</A>
      </c:if>
    
      <span class='top_menu_sep'>&nbsp;</span>    
      <A href="javascript:location.reload();">새로고침</A>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' 
                     style='width: 35%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' style='width: 35%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' 
                     onclick="location.href='./list.do?cateno=${cateVO.cateno}&word='">검색 취소</button>  
      </c:if>
      
    </ASIDE> 
    <DIV class='menu_line' style='clear: both;'></DIV>
  </form>
  
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>        
        <col style="width: 50%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>파일</th>          
          <th style='text-align: center;'>제목</th>
          <th style='text-align: center;'>회원</th>
          <th style='text-align: center;'>IP</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="contentsVO" items="${list }">
          <c:set var="contentsno" value="${contentsVO.contentsno }" />
          <c:set var="title" value="${contentsVO.title }" />
          <c:set var="content" value="${contentsVO.content }" />
          <c:set var="thumb1" value="${contentsVO.thumb1 }" />
          <c:set var="grpno" value="${contentsVO.grpno }" />
          <c:set var="indent" value="${contentsVO.indent }" />
          <c:set var="ansnum" value="${contentsVO.ansnum }" />
          <c:set var="cnt" value="${contentsVO.cnt }" />
          
          <tr style='height: 90px;'>
            <td style='vertical-align: middle; text-align: center;'>
            <c:choose>
            <c:when test="${ansnum > 0 }"> <%-- 자식글 아이콘 출력, 들여 쓰기 --%>
                <img src='./images/white.png' style='width: ${indent * 20}px; height: 40px; opacity: 0.0;'>
                <img src='./images/reply3.png'>
            </c:when>
            </c:choose>
            ${contentsVO.rdate.substring(0, 10)}
            </td>
            <td style='vertical-align: middle; text-align: center;'>
              <%-- grpno, indent, ansnum --%>
              <%-- ${contentsVO.grpno } / ${contentsVO.indent } / ${contentsVO.ansnum }  --%>
              <c:choose>
                <c:when test="${thumb1.toLowerCase().endsWith('jpg') || thumb1.toLowerCase().endsWith('png') || thumb1.toLowerCase().endsWith('gif') || thumb1.toLowerCase().endsWith('jpeg')}">
                  <IMG src="./storage/main_images/${thumb1 }" style='width: 120px; height: 80px;'> 
                </c:when>
                <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                  ${contentsVO.file1}
                </c:otherwise>
              </c:choose>
            </td>             
            <td style='vertical-align: middle;'>
              <c:choose>
                  <c:when test="${ansnum == 0 }"> <%-- 부모글인 경우 아이콘 출력 --%>
                      <img src='./images/ting1.png'>
                  </c:when>
              </c:choose>
              <span style='font-size: 1.2em; font-weight: bold;'>
                  <a href="./read.do?contentsno=${contentsno}">${title}</a>
              </span>
            </td> 
            <td style='vertical-align: middle; text-align: center;'>${contentsVO.memno}</td>
            <td style='vertical-align: middle; text-align: center;'>${contentsVO.ip}</td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <DIV class='bottom_menu'>${paging }</DIV>
    <br><br>
  </div>
</div> 
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html>
    
 