<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<style type="text/css">
/* fieldset 전체 적용 */
.categrp_cate_left{
  width: 100%;
  margin: 0px auto; 
  border-left: none; 
  border-right: none;
  border-top: solid 1px #DDDDDD;  
  border-bottom: solid 1px #DDDDDD;  
  text-align: left;
}
 
/* <UL> 태그에 적용 */
.categrp_cate_list_left{
  padding-left: 10%; 
  line-height: 20px;
}
 
/* 카테고리 그룹 이름에 적용, Spring에서 사용 */
.categrp_name {
  margin-left: 5%;
  font-weight: bold;
  list-style-image: url("${pageContext.request.contextPath}/cate/images/categrp.png");
}
 
/* 카테고리에 적용, Spring에서 사용 */
.cate_name{
  margin-left: 5%;
  list-style-image: url("${pageContext.request.contextPath}/cate/images/cate.png");
}
</style>
 
<fieldset class='categrp_cate_left' >
  <DIV>
    ▷
    <A href="${pageContext.request.contextPath}/contents/list_all.do">전체 보기</A>
    <span style='font-size:0.9em; color: #555555;'>(${total_count })</span>
  </DIV>
  <UL class='categrp_cate_list_left' >
    <c:forEach var="categrp_cate_name" items="${name_title_list}">
      ${categrp_cate_name}
    </c:forEach>
  </UL>
</fieldset>
<br>
   
   