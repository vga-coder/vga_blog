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
<script type="text/javascript">
  $(function() { // 자동 실행
    $('#btn_delete_youtube').on('click', youtube_delete_send); 
  });

  function youtube_delete_modal() {
    $('#modal_panel_youtube').modal();              // 다이얼로그 출력
  }
  
  // jQuery ajax 요청
  function youtube_delete_send () {
    // alert('삭제 진행');
    // return;
    
    var frm = $('#frm_youtube');  // Youtube 삭제 폼
    var contentsno = $('#contentsno', frm).val();
    var passwd = $('#passwd', frm).val();
    
    var params = 'contentsno=' + contentsno + '&passwd=' + passwd;
    var msg = '';
  
    $.ajax({
      url: './passwd.do',
      type: 'get',  // post
      cache: false, // 응답 결과 임시 저장 취소
      async: true,  // true: 비동기 통신
      dataType: 'json', // 응답 형식: json, html, xml...
      data: params,      // 데이터
      success: function(rdata) { // 서버로부터 성공적으로 응답이 온경우
        // alert(rdata);
        var msg = "";
          
        if (rdata.cnt > 0) { // 패스워드 일치
          frm.submit();
        } else {  // 패스워드 불일치
          msg = "패스워드가 일치하지 않습니다.";
          
          $('#modal_content').attr('class', 'alert alert-danger'); // Bootstrap CSS 변경
          $('#modal_title').html('패스워드 에러'); // 제목 
          $('#modal_content').html(msg);        // 내용
          $('#modal_panel').modal();              // 다이얼로그 출력
        }
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var msg = 'ERROR<br><br>';
        msg += '<strong>request.status</strong><br>'+request.status + '<hr>';
        msg += '<strong>error</strong><br>'+error + '<hr>';
        console.log(msg);
      }
    });
  }

  function panel_img(file) {
    var tag = "";
    tag   = "<A href=\"javascript: $('#attachfile_panel').hide();\">"; // 이미지 감추기
    tag += "  <IMG src='../attachfile/storage/" + file + "' style='width: 100%;'>";
    tag += "</A>";

    $('#attachfile_panel').html(tag);  // 문자열을 태그로 적용
    $('#attachfile_panel').show();      // 패널 출력
  }
  
</script>
</head>

<body>
<c:set var="cateno" value="${cateVO.cateno}" />
<c:set var="contentsno" value="${contentsVO.contentsno }" />

<jsp:include page="/menu/top.jsp" flush='false' />

  <!-- Modal Youtube 삭제 요청 시작 -->
  <div class="modal fade" id="modal_panel_youtube" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title">Youtube 삭제</h4><!-- 제목 -->
        </div>
        <div class="modal-body">
          <p>
            <form name='frm_youtube' id='frm_youtube' action='./youtube_delete.do'
                      method='POST'>
              <input type='hidden' name='cateno' id='cateno' value='${cateno }'>                      
              <input type='hidden' name='contentsno' id='contentsno' value='${contentsno }'>
              삭제된 Youtube 영상 관련 정보는 복구 할 수 없습니다.<br>
              <input type='password' class="form-control" name='passwd' id='passwd' value='' placeholder="패스워드" style='width: 20%;'>
            </form>
          </p>  <!-- 내용 -->
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
          <button type="button" id="btn_delete_youtube" class="btn btn-default" data-dismiss="modal">삭제</button>
        </div>
      </div>
    </div>
  </div> <!-- Modal Youtube 삭제 요청  종료 -->
  
  <!-- Modal 알림창 시작 -->
  <div class="modal fade" id="modal_panel" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title" id='modal_title'></h4><!-- 제목 -->
        </div>
        <div class="modal-body">
          <p id='modal_content'></p>  <!-- 내용 -->
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
  </div> <!-- Modal 알림창 종료 -->
  
  <DIV class='title_line'>
    ${cateVO.name }
  </DIV>
  
  <ASIDE style='float: left;'>
    ${categrpVO.name } > ${cateVO.name }
  </ASIDE>
  <ASIDE style='float: right;'>
    <A href='./reply.do?contentsno=${contentsno }&cateno=${cateno }'>답변</A>
    <span class='menu_divide' > | </span>
    <A href='../attachfile/create.do?contentsno=${contentsno }&cateno=${cateno }'>파일 등록</A>
    <span class='menu_divide' > | </span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' > | </span> 
    <A href='./list.do?cateno=${cateno }'>목록</A>
    
    <c:if test="${sessionScope.id != null or sessionScope.memberno != null}">
    
    <span class='menu_divide' > | </span>
    <A href='./update.do?cateno=${cateno }&contentsno=${contentsno}'>수정</A>
    <c:choose>
      <c:when test="${contentsVO.map.trim().length() > 0 }">
        <span class='menu_divide' > | </span> 
        <A href='./map_delete.do?cateno=${cateno }&contentsno=${contentsno}'>지도 삭제</A>     
      </c:when>
      <c:otherwise>
        <span class='menu_divide' > | </span> 
        <A href='./map_create.do?cateno=${cateno }&contentsno=${contentsno}'>지도 등록</A>     
      </c:otherwise>
    </c:choose>
    
    <c:choose>
      <c:when test="${contentsVO.youtube.trim().length() > 0 }">
        <span class='menu_divide' > | </span> 
        <A href='javascript:youtube_delete_modal()'>Youtube 삭제</A>     
      </c:when>
      <c:otherwise>
        <span class='menu_divide' > | </span> 
        <A href='./youtube_create.do?cateno=${cateno }&contentsno=${contentsno}'>Youtube 등록</A>     
      </c:otherwise>
    </c:choose>
    
    <c:choose>
      <c:when test="${contentsVO.mp3.trim().length() > 0 }">
        <span class='menu_divide' > | </span> 
        <A href='./mp3_delete.do?cateno=${cateno }&contentsno=${contentsno}'>MP3 삭제</A>     
      </c:when>
      <c:otherwise>
        <span class='menu_divide' > | </span> 
        <A href='./mp3_create.do?cateno=${cateno }&contentsno=${contentsno}'>MP3 등록</A>     
      </c:otherwise>
    </c:choose>

    <c:choose>
      <c:when test="${contentsVO.mp4.trim().length() > 0 }">
        <span class='menu_divide' > | </span> 
        <A href='./mp4_delete.do?cateno=${cateno }&contentsno=${contentsno}'>MP4 삭제</A>     
      </c:when>
      <c:otherwise>
        <span class='menu_divide' > | </span> 
        <A href='./mp4_create.do?cateno=${cateno }&contentsno=${contentsno}'>MP4 등록</A>     
      </c:otherwise>
    </c:choose>
        
    <span class='menu_divide' > | </span> 
    <A href='./delete.do?cateno=${cateno }&contentsno=${contentsno}'>삭제</A>
    </c:if>
  </ASIDE> 
  
  <div class='menu_line'></div>

  <FORM name='frm' method="get" action='./update.do'>
      <input type="hidden" name="contentsno" value="${contentsno}">
      <fieldset class="fieldset">
        <ul>
          <li class="li_none" style='border-bottom: solid 1px #AAAAAA;'>
            <span>${contentsVO.title}</span>
            (<span>${contentsVO.recom}</span>)
            <span>${contentsVO.rdate.substring(0, 16)}</span>
          </li>
          
          <li class="li_none">
            <DIV id='attachfile_panel' style="width: 70%; margin: 0px auto;"></DIV> <!-- 원본 이미지 출력 -->
          </li>
          <li class="li_none" style='text-align: center;' >
            <c:forEach var="attachfileVO" items="${attachfile_list }">
              <c:set var="thumb" value="${attachfileVO.thumb.toLowerCase() }" />
              
              <c:choose>
                <c:when test="${thumb.endsWith('jpg') || thumb.endsWith('png') || thumb.endsWith('gif')}">
                  <A href="javascript:panel_img('${attachfileVO.fname }')"><IMG src='../attachfile/storage/${thumb }' style='margin-top: 2px;'></A>
                </c:when>
              </c:choose>
            </c:forEach>
          </li>
          
          <li class="li_none">
            <DIV>${contentsVO.content }</DIV>
          </li>
          <li class="li_none">
            ${contentsVO.web }
          </li>
          
          <c:if test="${contentsVO.map.trim().length() > 0 }">
            <li class='li_none' style='clear: both;'>
              <DIV style='width:640px; height: 380px; margin: 0px auto;'>
                ${contentsVO.map }
              </DIV>
            </li>
          </c:if>
          
          <c:if test="${contentsVO.youtube.trim().length() > 0 }">
            <li class='li_none' style='clear: both;'>
              <DIV style='width:900px; margin: 0px auto; text-align: center;'>
                ${contentsVO.youtube}
              </DIV>
            </li>
          </c:if>          

          <c:if test="${contentsVO.mp4.trim().length() > 0 }">
            <li class='li_none' style='clear: both;'>
              <DIV style='width:900px; margin: 0px auto; text-align: center;'>
                <VIDEO controls src='./storage/mp4/${contentsVO.mp4}'
                            style='width: 70%; background-color: #EEEEEE;'></VIDEO>
              </DIV>
            </li>
          </c:if>   
          
          <li class="li_none">
            <DIV style='text-decoration: none;'>
              검색어(키워드): ${contentsVO.word } IP: ${contentsVO.ip }
              
              <c:if test="${contentsVO.mp3.trim().length() > 0 }">
                <AUDIO controls autoplay="autoplay">
                  <source src="./storage/mp3/${contentsVO.mp3 }" type="audio/mp3">
                </AUDIO>
              </c:if>  
          
            </DIV>
          </li>
          
        </ul>
      </fieldset>
  </FORM>

<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

</html>
 

