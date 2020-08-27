<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>vgaBlog</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    
<script type="text/javascript">
  $(function(){ 
  
  });
</script>
<style>
.li_none {
    list-style: none;
    margin: 15px 10px;
}
</style> 
</head> 
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
  <DIV class="form" style="margin:100px auto; width:80%; text-align:center">
 
<DIV class='title_line'>알림</DIV>

<br><br>
오늘도 수고하셨습니다.
<br><br>
          [<A href='${pageContext.request.contextPath}/index.do'>확인</A>]
<br><br>   
  </DIV>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
 
</html>
 
 
 