<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="root" value="${pageContext.request.contextPath}" /> 

<DIV class='container' style='width: 100%;'> 
  <!-- 화면 상단 메뉴 -->
  <DIV>
        <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top navbar-shrink">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="${root }/index.jsp">vgaBlog</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden active">
                        <a href="#page-top"></a>
                    </li>
                    <li class="">
                        <a class="page-scroll" href="${root }/index.jsp#values">values</a>
                    </li>
                    <li class="">
                        <a class="page-scroll" href="${root }/index.jsp#portfolio">category</a>
                    </li>
                    <li class="">
                          <c:choose>
					        <%-- 로그인 안한 경우 --%>
					        <c:when test="${sessionScope.mem_id == null && sessionScope.id_admin == null}">
					          <A class='menu_link'  href='${root}/loginmode.jsp'>로그인</A><span class='top_menu_sep'>&nbsp;</span>    
					        </c:when>
					        <%-- 회원 로그인 한 경우 --%>
					        <c:when test="${sessionScope.mem_id != null}">
					          <A class='menu_link'  href='${root}/mem/logout.do'>${sessionScope.mem_id } 로그아웃</A><span class='top_menu_sep'>&nbsp;</span>    
					        </c:when>
					        <%-- 관리자 로그인 한 경우 --%>
					        <c:when test="${sessionScope.id_admin != null}">
					          <A class='menu_link'  href='${root}/admin/logout.do'>${sessionScope.id_admin } 로그아웃</A><span class='top_menu_sep'>&nbsp;</span>    
					        </c:when>
					      </c:choose> 
                    </li>
                    <li class="">
                        <a class="page-scroll" href="${root }/index.jsp#about">About</a>
                    </li>
                    <li class="">
                        <a class="page-scroll" href="${root }/index.jsp#team">Team</a>
                    </li>
                    <li class="">
                        <a class="page-scroll" href="${root }/index.jsp#contact">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
   </DIV>
</DIV>
   
   