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
    ${cateVO.name }
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
      <c:if test="${sessionScope.id != null or sessionScope.id_admin != null}">
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
    
    <!-- 갤러리 Layout 시작 -->
    <c:forEach var="contentsVO" items="${list }" varStatus="status">
      <c:set var="contentsno" value="${contentsVO.contentsno }" />
      <c:set var="thumb1" value="${contentsVO.thumb1.toLowerCase() }" />
      <c:set var="title" value="${contentsVO.title}" />
      <c:set var="cnt" value="${contentsVO.cnt}" />
      <c:set var="rdate" value="${contentsVO.rdate}" />
      <c:set var="memberno" value="${contentsVO.memberno}" />
      <c:set var="file1" value="${contentsVO.file1}" />
      <c:set var="size1" value="${contentsVO.size1}" />

      <c:if test="${status.index % 4 == 0 && status.index != 0 }">
        <HR class='menu_line'>
      </c:if>
      
      <!-- 하나의 이미지 -->
      <DIV style='width: 24%; 
               float: left; 
               margin: 0.5%; padding: 0.5%; background-color: #EEEFFF;'>
        <c:choose>
          <c:when test="${size1 > 0}"> <!-- 파일이 존재하면 -->
            <c:choose> 
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <!-- 이미지 인경우 -->
                <a href="./read.do?contentsno=${contentsno}">               
                  <IMG src="./storage/main_images/${thumb1 }" style='width: 100%; height: 150px;'>
                </a><br>
                ${contentsVO.title} (${contentsVO.cnt})
              </c:when>
              <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                <DIV style='width: 100%; height: 150px; display: table; border: solid 1px #CCCCCC;'>
                  <DIV style='display: table-cell; vertical-align: middle; text-align: center;'> <!-- 수직 가운데 정렬 -->
                    <a href="./read.do?contentsno=${contentsno}">${contentsVO.file1}</a><br>
                  </DIV>
                </DIV>
                ${contentsVO.title} (${contentsVO.cnt})              
              </c:otherwise>
            </c:choose>
          </c:when>
          <c:otherwise>
            <a href="./read.do?contentsno=${contentsno}">
              <img src='./images/none1.png' style='width: 100%; height: 150px;'>
            </a><br>
            이미지를 등록해주세요.
          </c:otherwise>
        </c:choose>         
      </DIV>  
    </c:forEach>
    <!-- 갤러리 Layout 종료 -->

    <DIV class='bottom_menu'>${paging }</DIV>
    <br><br>
  </div>
 
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html>
    
 