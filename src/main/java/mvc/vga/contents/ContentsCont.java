package mvc.vga.contents;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mvc.vga.attachfile.AttachfileProcInter;
import mvc.vga.attachfile.AttachfileVO;
import mvc.vga.cate.CateProcInter;
import mvc.vga.cate.CateVO;
import mvc.vga.categrp.CategrpProcInter;
import mvc.vga.categrp.CategrpVO;
import mvc.vga.mem.MemProcInter;
import mvc.vga.mem.MemVO;
import mvc.vga.tool.Tool;
import mvc.vga.tool.Upload;

@Controller
public class ContentsCont {
  @Autowired
  @Qualifier("mvc.vga.categrp.CategrpProc")
  private CategrpProcInter categrpProc;
  
  @Autowired
  @Qualifier("mvc.vga.cate.CateProc")
  private CateProcInter cateProc;
  
  @Autowired
  @Qualifier("mvc.vga.contents.ContentsProc")
  private ContentsProcInter contentsProc;

  @Autowired
  @Qualifier("mvc.vga.mem.MemProc")
  private MemProcInter memProc;

  @Autowired
  @Qualifier("mvc.vga.attachfile.AttachfileProc")
  private AttachfileProcInter attachfileProc;
  
  public ContentsCont() {
    System.out.println("--> ContentsCont created.");
  }
  
  // http://localhost:9090/resort/contents/create.do
  /**
   * 등록 폼
   * @return
   */
  @RequestMapping(value="/contents/create.do", method=RequestMethod.GET )
  public ModelAndView create(int cateno) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO);

    mav.setViewName("/contents/create"); // webapp/contents/create.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/create.do
  /**
   * 등록 처리
   * @param cateVO
   * @return
   */
  @RequestMapping(value="/contents/create.do", 
                              method=RequestMethod.POST )
  public ModelAndView create(HttpServletRequest request, ContentsVO contentsVO) {
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String file1 = "";     // main image
    String thumb1 = ""; // preview image
        
    String upDir = Tool.getRealPath(request, "/contents/storage/main_images"); // 절대 경로
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    MultipartFile mf = contentsVO.getFile1MF();
    long size1 = mf.getSize();  // 파일 크기
    if (size1 > 0) { // 파일 크기 체크
      // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 120, height: 80
        thumb1 = Tool.preview(upDir, file1, 250, 200); 
      }
      
    }    
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    contentsVO.setIp(request.getRemoteAddr()); // 접속자 IP
    
    /*
  <insert id="create" parameterType="ContentsVO">
    <!-- 등록후 contentsno return  -->
    <selectKey keyProperty="contentsno" resultType="int" order="BEFORE">
      SELECT contents_seq.nextval FROM dual
    </selectKey>
    INSERT INTO contents(contentsno, memberno, cateno, title, content, web, ip,
                                     passwd, word, rdate)
    VALUES(#{contentsno}, #{memberno}, #{cateno}, #{title}, #{content}, #{web}, #{ip},
                #{passwd}, #{word}, sysdate)
  </insert>
     * */
    // PK return 됨
    contentsVO.setFile1(file1);
    contentsVO.setThumb1(thumb1);
    contentsVO.setSize1(size1);
    int cnt = this.contentsProc.create(contentsVO); // Call By Reference로 전송
    System.out.println("cnt: " + cnt);
    mav.addObject("cnt", cnt);
    
    // ---------------------------------------------------------------------------------------
    // cnt, contentsno return 
    // ---------------------------------------------------------------------------------------
    // Spring <-----> contentsVO <-----> MyBATIS
    // Spring과 MyBATIS가 ContentsVO를 공유하고 있음.
    // MyBATIS는 insert후 PK 컬럼인 contentsno변수에 새로 생성된 PK를 할당함.
    int contentsno = contentsVO.getContentsno();  // MyBATIS 리턴된 PK
    System.out.println("contentsno: " + contentsno);
    mav.addObject("contentsno", contentsno); // request에 저장
    // ---------------------------------------------------------------------------------------
    
    mav.addObject("cateno", contentsVO.getCateno());
    mav.addObject("url", "create_msg"); // // webapp/contents/create_msg.jsp
    
    if (cnt == 1) {  // 정상적으로 글이 등록된 경우만 글 수 증가
      this.cateProc.increaseCnt(contentsVO.getCateno()); // 글수 증가
    }
    /*
     * // mav.setViewName("/contents/create_msg"); //
     * mav.setViewName("redirect:/contents/list.do"); // spring 재호출
     * mav.setViewName("redirect:/contents/msg.do"); } else { //
     * mav.setViewName("/contents/create_msg"); // webapp/contents/create_msg.jsp }
     */    
    
    mav.setViewName("redirect:/contents/msg.do");
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/list_all.do
  /**
   * 전체 목록
   * @return
   */
  @RequestMapping(value="/contents/list_all.do", method=RequestMethod.GET )
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    List<ContentsVO> list = this.contentsProc.list_all();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/contents/list_all"); // /webapp/contents/list_all.jsp
    return mav;
  }
  
  // http://localhost:9090/resort/contents/list.do
  // 전체 목록
//  @RequestMapping(value="/contents/list.do", method=RequestMethod.GET )
//  public ModelAndView list(int cateno) {
//    ModelAndView mav = new ModelAndView();
//    
//    CateVO cateVO = this.cateProc.read(cateno);
//    mav.addObject("cateVO", cateVO); 
//    
//    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
//    mav.addObject("categrpVO", categrpVO); 
//    
//    List<ContentsVO> list = this.contentsProc.list(cateno);
//    mav.addObject("list", list); 
//
//    mav.setViewName("/contents/list"); // /webapp/contents/list.jsp
//    return mav;
//  }  

  /**
   * 목록 + 검색 지원
   * http://localhost:9090/resort/contents/list.do
   * http://localhost:9090/resort/contents/list.do?cateno=1&word=스위스
   * @param cateno
   * @param word
   * @return
   */
  /*
   * @RequestMapping(value = "/contents/list.do", method = RequestMethod.GET)
   * public ModelAndView list_by_cateno_search(
   * 
   * @RequestParam(value="cateno", defaultValue="1") int cateno,
   * 
   * @RequestParam(value="word", defaultValue="") String word ) {
   * 
   * // System.out.println("--> word: " + word);
   * 
   * ModelAndView mav = new ModelAndView(); // /contents/list_by_cateno_search.jsp
   * 
   * // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용 HashMap<String, Object> map = new
   * HashMap<String, Object>(); map.put("cateno", cateno); // #{cateno}
   * map.put("word", word); // #{word}
   * 
   * // 검색 목록 List<ContentsVO> list = contentsProc.list_by_cateno_search(map);
   * mav.addObject("list", list);
   * 
   * // 검색된 레코드 갯수 int search_count = contentsProc.search_count(map);
   * mav.addObject("search_count", search_count);
   * 
   * CateVO cateVO = cateProc.read(cateno); mav.addObject("cateVO", cateVO);
   * 
   * CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
   * mav.addObject("categrpVO", categrpVO);
   * 
   * mav.setViewName("/contents/list_by_cateno_search");
   * 
   * return mav; }
   */    
    
  
  // http://localhost:9090/resort/contents/read.do
  /**
   * 전체 목록
   * @return
   */
  @RequestMapping(value="/contents/read.do", method=RequestMethod.GET )
  public ModelAndView read(int contentsno) {
    ModelAndView mav = new ModelAndView();

    ContentsVO contentsVO = this.contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO); // request.setAttribute("contentsVO", contentsVO);

    CateVO cateVO = this.cateProc.read(contentsVO.getCateno());
    mav.addObject("cateVO", cateVO); 

    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO); 
    
    // 첨부 파일 목록
    List<AttachfileVO> attachfile_list = this.attachfileProc.list_by_contentsno(contentsno);
    mav.addObject("attachfile_list", attachfile_list); 
    
    mav.setViewName("/contents/read"); // /webapp/contents/read.jsp
    return mav;
  }
  
  // http://localhost:9090/resort/contents/update.do
  /**
   * 수정 폼
   * @return
   */
  @RequestMapping(value="/contents/update.do", method=RequestMethod.GET )
  public ModelAndView update(int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    ContentsVO contentsVO = this.contentsProc.update(contentsno);
    mav.addObject("contentsVO", contentsVO); // request.setAttribute("contentsVO", contentsVO);
    
    mav.setViewName("/contents/update"); // webapp/contents/update.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/update.do
  /**
   * 수정 처리
   * @param contentsVO
   * @return
   */
  @RequestMapping(value="/contents/update.do", method=RequestMethod.POST )
  public ModelAndView update(ContentsVO contentsVO) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(contentsVO.getCateno());
    // mav.addObject("cateVO", cateVO); // 전달안됨.
    mav.addObject("cate_name", cateVO.getName());
    mav.addObject("cateno", cateVO.getCateno());

    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    // mav.addObject("categrpVO", categrpVO); // 전달안됨.
    mav.addObject("categrp_name", categrpVO.getName());
    
    mav.addObject("contentsno", contentsVO.getContentsno());
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("contentsno", contentsVO.getContentsno());
    hashMap.put("passwd", contentsVO.getPasswd());
    
    int passwd_cnt = 0; // 패스워드 일치 레코드 갯수
    int cnt = 0;             // 수정된 레코드 갯수 
    
    passwd_cnt = this.contentsProc.passwd_check(hashMap);
    
    if (passwd_cnt == 1) { // 패스워드 일치
      cnt = this.contentsProc.update(contentsVO);
    }

    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("passwd_cnt", passwd_cnt); // request에 저장
        
    if (passwd_cnt == 1 && cnt == 1) {
      mav.setViewName("redirect:/contents/update_msg.jsp"); // webapp/contents/update_msg.jsp
      // mav.setViewName("/contents/update_msg"); // webapp/contents/update_msg.jsp
      // mav.setViewName("redirect:/contents/list.do"); // spring 재호출
    } else { 
      mav.setViewName("/contents/update_msg"); // webapp/contents/update_msg.jsp
    }
    
    return mav;
  }

// 문제가 있는 패턴  
// http://localhost:9090/resort/contents/delete.do
//  @RequestMapping(value="/contents/delete.do", method=RequestMethod.GET )
//  public ModelAndView delete(HttpSession session, int contentsno) {
//    ModelAndView mav = new ModelAndView();
//    
//    if (session.getAttribute("memberno") != null) {
//      ContentsVO contentsVO = this.contentsProc.update(contentsno);
//      mav.addObject("contentsVO", contentsVO); // request.setAttribute("contentsVO", contentsVO);
//      
//      mav.setViewName("/contents/delete"); // webapp/contents/delete.jsp
//    } else {
//      mav.setViewName("redirect:/member/login_need.jsp");
//    }
//      
//    return mav;
//  }

  /**
   * 글 작성자가 일치하는지 확인하여 삭제
   * @param session
   * @param contentsno 글번호
   * @return
   */
@RequestMapping(value="/contents/delete.do", method=RequestMethod.GET )
public ModelAndView delete(HttpSession session, int contentsno) {
  ModelAndView mav = new ModelAndView();
  
  int memno = (Integer)session.getAttribute("memno");
  
  // 글 작성자가 일치하는지 확인
  if (memno == contentsProc.read(contentsno).getMemno()) {
    ContentsVO contentsVO = this.contentsProc.update(contentsno);
    mav.addObject("contentsVO", contentsVO); // request.setAttribute("contentsVO", contentsVO);
    
    mav.setViewName("/contents/delete"); // webapp/contents/delete.jsp
  } else {
    mav.setViewName("redirect:/member/mconfirm_fail_msg.jsp");
  }
    
  return mav;
}
  
  // http://localhost:9090/resort/contents/delete.do
  /**
   * 삭제 처리
   * @param contentsVO
   * @return
   */
  @RequestMapping(value="/contents/delete.do", method=RequestMethod.POST )
  public ModelAndView delete(int contentsno, String passwd) {
    ModelAndView mav = new ModelAndView();

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("contentsno", contentsno);
    hashMap.put("passwd", passwd);
    
    int passwd_cnt = 0; // 패스워드 일치 레코드 갯수
    int cnt = 0;             // 삭제된 레코드 갯수 
    
    passwd_cnt = this.contentsProc.passwd_check(hashMap);
    
    ContentsVO contentsVO = this.contentsProc.read(contentsno); 
    String title = contentsVO.getTitle(); // 제목
    mav.addObject("title", title);
    
    if (passwd_cnt == 1) { // 패스워드 일치
      cnt = this.contentsProc.delete(contentsno);
    }

    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("passwd_cnt", passwd_cnt); // request에 저장
        
    if (passwd_cnt == 1 && cnt == 1) {
      this.cateProc.decreaseCnt(contentsVO.getCateno());  // 글수 감소
      mav.setViewName("/contents/delete_msg"); // webapp/contents/delete_msg.jsp
      // mav.setViewName("redirect:/contents/list.do"); // spring 재호출
    } else { 
      mav.setViewName("/contents/delete_msg"); // webapp/contents/delete_msg.jsp
    }
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/list_by_memberno.do
  /**
   * 회원별 글 전체 목록
   * @return
   */
  @RequestMapping(value="/contents/list_by_memberno.do", method=RequestMethod.GET )
  public ModelAndView list_by_memberno(int memno) {
    ModelAndView mav = new ModelAndView();
    
    // Type 1
    MemVO memVO = this.memProc.read(memno);
    List<Cate_Contents_VO> list = this.contentsProc.cate_contents_memberno_list(memno);
    mav.addObject("memVO", memVO); 
    mav.addObject("list", list); 
    
    // Type 2
//    Cate_Contents_Member_VO vo = new Cate_Contents_Member_VO();
//    vo.setMemberVO(memberVO);
//    vo.setCate_contents_memberno_list(list);
//    mav.addObject("vo", vo);

    mav.setViewName("/contents/list_by_memberno"); // /webapp/contents/list_by_memberno.jsp
    return mav;
  }  
  
  // http://localhost:9090/resort/contents/map_create.do?cateno=25&contentsno=28
  /**
   * 지도 등록 폼
   * @return
   */
  @RequestMapping(value="/contents/map_create.do", method=RequestMethod.GET )
  public ModelAndView map_create(int cateno, int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO);
    
    ContentsVO contentsVO = this.contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);

    mav.setViewName("/contents/map_create"); // webapp/contents/map_create.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/passwd.do?contentsno=28&passwd=123
  /**
   * 글의 패스워드 체크, JSON 출력
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/contents/passwd.do", method=RequestMethod.GET ,
                              produces = "text/plain;charset=UTF-8" )
  public String passwd(int contentsno, String passwd) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("contentsno", contentsno);
    hashMap.put("passwd", passwd);
    
    int cnt = this.contentsProc.passwd_check(hashMap);
    
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
    
    return json.toString(); 
  }
  
  // http://localhost:9090/resort/contents/map_create.do?cateno=25&contentsno=28
  /**
   * 지도 등록
   * @param cateno 카테고리 번호
   * @param contentsno 글번호
   * @param map 지도 스크립트
   * @param passwd 패스워드 
   * @return
   */
  @RequestMapping(value="/contents/map_create.do", method=RequestMethod.POST )
  public ModelAndView map_create(int cateno, int contentsno, String map, String passwd) {
    ModelAndView mav = new ModelAndView();

    // System.out.println("map: " + map);
    // System.out.println("contentsno: " + contentsno);
    
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("map", map);
    hashMap.put("contentsno", contentsno);
    hashMap.put("passwd", passwd);
    
    this.contentsProc.map(hashMap); // 지도 등록
    
    mav.addObject("contentsno", contentsno);
    
    mav.setViewName("redirect:/contents/read.do"); // webapp/contents/list.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/map_delete.do?cateno=25&contentsno=28
  /**
   * 지도 삭제 폼
   * @return
   */
  @RequestMapping(value="/contents/map_delete.do", method=RequestMethod.GET )
  public ModelAndView map_delete(int cateno, int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO);
    
    ContentsVO contentsVO = this.contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);

    mav.setViewName("/contents/map_delete"); // webapp/contents/map_delete.jsp
    
    return mav;
  }

  // http://localhost:9090/resort/contents/map_delete.do?cateno=25&contentsno=28
  /**
   * 지도 삭제 처리
   * @param cateno 카테고리 번호
   * @param contentsno 글번호
   * @param paswd 패스워드 
   * @param map 지도 스크립트
   * @return
   */
  @RequestMapping(value="/contents/map_delete.do", method=RequestMethod.POST )
  public ModelAndView map_delete_proc(int cateno, int contentsno, String passwd) {
    ModelAndView mav = new ModelAndView();

    // System.out.println("map: " + map);
    // System.out.println("contentsno: " + contentsno);
    
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("map", ""); // map 삭제
    hashMap.put("contentsno", contentsno);
    hashMap.put("passwd", passwd);
        
    this.contentsProc.map(hashMap); // 지도 삭제 처리
    
    mav.addObject("contentsno", contentsno);
    
    mav.setViewName("redirect:/contents/read.do"); // webapp/contents/list.jsp
    
    return mav;
  }

  // http://localhost:9090/resort/contents/youtube_create.do?cateno=25&contentsno=28
  /**
   * Youtube 등록 폼
   * @return
   */
  @RequestMapping(value="/contents/youtube_create.do", 
                              method=RequestMethod.GET )
  public ModelAndView youtube_create(int cateno, int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO);
    
    ContentsVO contentsVO = this.contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);

    mav.setViewName("/contents/youtube_create"); // webapp/contents/youtube_create.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/youtube_create.do?cateno=25&contentsno=28
  /**
   * Youtube 등록
   * @param cateno 카테고리 번호
   * @param contentsno 글번호
   * @param youtube 지도 스크립트
   * @return
   */
  @RequestMapping(value="/contents/youtube_create.do", 
                              method=RequestMethod.POST )
  public ModelAndView youtube_create(int cateno, int contentsno, String youtube, String passwd) {
    ModelAndView mav = new ModelAndView();

    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("youtube", youtube);
    hashMap.put("contentsno", contentsno);
    hashMap.put("passwd", passwd);
    
    this.contentsProc.youtube(hashMap); // 지도 등록
    
    mav.addObject("contentsno", contentsno);
    
    mav.setViewName("redirect:/contents/read.do"); // webapp/contents/list.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/youtube_delete.do?cateno=25&contentsno=28
  /**
   * 지도 삭제 처리
   * @param cateno 카테고리 번호
   * @param contentsno 글번호
   * @param paswd 패스워드  
   * @param map 지도 스크립트
   * @return
   */
  @RequestMapping(value="/contents/youtube_delete.do", method=RequestMethod.POST )
  public ModelAndView youtube_delete_proc(int cateno, int contentsno, String passwd) {
    ModelAndView mav = new ModelAndView();

    // System.out.println("map: " + map);
    // System.out.println("contentsno: " + contentsno);
    
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("youtube", ""); // map 삭제
    hashMap.put("contentsno", contentsno);
    hashMap.put("passwd", passwd);
    
    this.contentsProc.youtube(hashMap); // 지도 삭제 처리
    
    mav.addObject("contentsno", contentsno);
    
    mav.setViewName("redirect:/contents/read.do"); // webapp/contents/list.jsp
    
    return mav;
  }
  
  /**
   * 메시지
   * @param url 이동할 jsp 주소
   * @return
   */
  @RequestMapping(value="/contents/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();
    
    // 등록 처리 메시지: create_msg --> /contents/create_msg.jsp
    // 수정 처리 메시지: update_msg --> /contents/update_msg.jsp
    // 삭제 처리 메시지: delete_msg --> /contents/delete_msg.jsp
    mav.setViewName("/contents/" + url); // forward
    
    return mav; // forward
  }
  
  // http://localhost:9090/resort/contents/mp3_create.do?cateno=25&contentsno=28
  /**
   * MP3 등록 폼
   * @return
   */
  @RequestMapping(value="/contents/mp3_create.do", 
                              method=RequestMethod.GET )
  public ModelAndView mp3_create(int cateno, int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO);
    
    ContentsVO contentsVO = this.contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);

    mav.setViewName("/contents/mp3_create"); // webapp/contents/mp3_create.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/mp3_create.do?cateno=25&contentsno=28
  /**
   * MP3 등록
   * @param cateno 카테고리 번호
   * @param contentsno 글번호
   * @param youtube 지도 스크립트
   * @return
   */
  @RequestMapping(value="/contents/mp3_create.do", 
                              method=RequestMethod.POST )
  public ModelAndView mp3_create(HttpServletRequest request,
                                                    ContentsVO contentsVO) {
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String mp3 = ""; // mp3 파일
    
    String upDir = Tool.getRealPath(request, "/contents/storage/mp3"); // 절대 경로
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    MultipartFile mf = contentsVO.getMp3MF();  // 파일 목록
    long fsize = mf.getSize();  // 파일 크기
    if (fsize > 0) { // 파일 크기 체크
      // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      mp3 = Upload.saveFileSpring(mf, upDir); 
    }    
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("mp3", mp3);
    hashMap.put("contentsno", contentsVO.getContentsno());
    hashMap.put("passwd", contentsVO.getPasswd());
    
    this.contentsProc.mp3(hashMap); 
    
    mav.addObject("contentsno", contentsVO.getContentsno());
    
    mav.setViewName("redirect:/contents/read.do"); // webapp/contents/read.jsp
    
    return mav;
  }
    
  // http://localhost:9090/resort/contents/mp3_delete.do?cateno=25&contentsno=28
  /**
   * MP3 삭제 폼
   * @return
   */
  @RequestMapping(value="/contents/mp3_delete.do", 
                              method=RequestMethod.GET )
  public ModelAndView mp3_delete(int cateno, int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO);
    
    ContentsVO contentsVO = this.contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);

    mav.setViewName("/contents/mp3_delete"); // webapp/contents/mp3_delete.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/mp3_delete.do?cateno=25&contentsno=28
  /**
   * MP3 삭제 처리
   * @param cateno 카테고리 번호
   * @param contentsno 글번호
   * @param paswd 패스워드  
   * @param map 지도 스크립트
   * @return
   */
  @RequestMapping(value="/contents/mp3_delete.do", 
                              method=RequestMethod.POST )
  public ModelAndView mp3_delete_proc(HttpServletRequest request,
                                                            int cateno, 
                                                            int contentsno, 
                                                            String passwd) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    ContentsVO contentsVO = contentsProc.read(contentsno);
    System.out.println("mp3: " + contentsVO.getMp3());
    
    String upDir = Tool.getRealPath(request, "/contents/storage/mp3"); // 절대 경로
    boolean sw = Tool.deleteFile(upDir, contentsVO.getMp3());  // Folder에서 1건의 파일 삭제
    System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("mp3", ""); // map 삭제
    hashMap.put("contentsno", contentsno);
    hashMap.put("passwd", passwd);
    
    this.contentsProc.mp3(hashMap);
    
    mav.addObject("contentsno", contentsno);
    
    mav.setViewName("redirect:/contents/read.do"); // webapp/contents/list.jsp
    
    return mav;
  }

  // http://localhost:9090/resort/contents/mp4_create.do?cateno=25&contentsno=28
  /**
   * MP4 등록 폼
   * @return
   */
  @RequestMapping(value="/contents/mp4_create.do", 
                              method=RequestMethod.GET )
  public ModelAndView mp4_create(int cateno, int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO);
    
    ContentsVO contentsVO = this.contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);

    mav.setViewName("/contents/mp4_create"); // webapp/contents/mp4_create.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/mp4_create.do?cateno=25&contentsno=28
  /**
   * MP4 등록
   * @param cateno 카테고리 번호
   * @param contentsno 글번호
   * @param youtube 지도 스크립트
   * @return
   */
  @RequestMapping(value="/contents/mp4_create.do", 
                              method=RequestMethod.POST )
  public ModelAndView mp4_create(HttpServletRequest request,
                                                    ContentsVO contentsVO) {
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String mp4 = ""; // mp3 파일
    
    String upDir = Tool.getRealPath(request, "/contents/storage/mp4"); // 절대 경로
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    MultipartFile mf = contentsVO.getMp4MF();  // 파일 목록
    long fsize = mf.getSize();  // 파일 크기
    if (fsize > 0) { // 파일 크기 체크
      // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      mp4 = Upload.saveFileSpring(mf, upDir); 
    }    
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("mp4", mp4);
    hashMap.put("contentsno", contentsVO.getContentsno());
    hashMap.put("passwd", contentsVO.getPasswd());
    
    this.contentsProc.mp4(hashMap); 
    
    mav.addObject("contentsno", contentsVO.getContentsno());
    
    mav.setViewName("redirect:/contents/read.do"); // webapp/contents/read.jsp
    
    return mav;
  }
    
  // http://localhost:9090/resort/contents/mp4_delete.do?cateno=25&contentsno=28
  /**
   * MP4 삭제 폼
   * @return
   */
  @RequestMapping(value="/contents/mp4_delete.do", 
                              method=RequestMethod.GET )
  public ModelAndView mp4_delete(int cateno, int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO);
    
    ContentsVO contentsVO = this.contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);

    mav.setViewName("/contents/mp4_delete"); // webapp/contents/mp4_delete.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/contents/mp4_delete.do?cateno=25&contentsno=28
  /**
   * MP4 삭제 처리
   * @param cateno 카테고리 번호
   * @param contentsno 글번호
   * @param paswd 패스워드  
   * @param map 지도 스크립트
   * @return
   */
  @RequestMapping(value="/contents/mp4_delete.do", 
                              method=RequestMethod.POST )
  public ModelAndView mp4_delete_proc(HttpServletRequest request,
                                                            int cateno, 
                                                            int contentsno, 
                                                            String passwd) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    ContentsVO contentsVO = contentsProc.read(contentsno);
    // System.out.println("mp4: " + contentsVO.getMp4());
    
    String upDir = Tool.getRealPath(request, "/contents/storage/mp4"); // 절대 경로
    boolean sw = Tool.deleteFile(upDir, contentsVO.getMp4());  // Folder에서 1건의 파일 삭제
    // System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("mp4", ""); // map 삭제
    hashMap.put("contentsno", contentsno);
    hashMap.put("passwd", passwd);
    
    this.contentsProc.mp4(hashMap);
    
    mav.addObject("contentsno", contentsno);
    
    mav.setViewName("redirect:/contents/read.do"); // webapp/contents/list.jsp
    
    return mav;
  }
  
  /**
   * 목록 + 검색 + 페이징 지원
   * http://localhost:9090/resort/contents/list.do
   * http://localhost:9090/resort/contents/list.do?cateno=1&word=스위스&nowPage=1
   * @param cateno
   * @param word
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/contents/list.do", 
                                       method = RequestMethod.GET)
  public ModelAndView list_by_cateno_search_paging(
      @RequestParam(value="cateno", defaultValue="1") int cateno,
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) { 
    System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
    
    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("cateno", cateno); // #{cateno}
    map.put("word", word);     // #{word}
    map.put("nowPage", nowPage);  // 페이지에 출력할 레코드의 범위를 산출하기위해 사용     
    
    // 검색 목록
    List<ContentsVO> list = contentsProc.list_by_cateno_search_paging(map);
    mav.addObject("list", list);
    
    // 검색된 레코드 갯수
    int search_count = contentsProc.search_count(map);
    mav.addObject("search_count", search_count);
  
    CateVO cateVO = cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    CategrpVO categrpVO = categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO);

    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
     * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
     * 
     * @param listFile 목록 파일명 
     * @param cateno 카테고리번호 
     * @param search_count 검색(전체) 레코드수 
     * @param nowPage     현재 페이지
     * @param word 검색어
     * @return 페이징 생성 문자열
     */ 
    String paging = contentsProc.pagingBox("list.do", cateno, search_count, nowPage, word);
    mav.addObject("paging", paging);
  
    mav.addObject("nowPage", nowPage);

    // /contents/list_by_cateno_search_paging.jsp
    
    // 테이블 기반 텍스트 목록
    // mav.setViewName("/contents/list_by_cateno_search_paging_img_table1");
    
    // 테이블 기반 이미지 목록
    // mav.setViewName("/contents/list_by_cateno_search_paging_img_table2");  
    
    // 갤러리형
    // mav.setViewName("/contents/list_by_cateno_search_paging_img_grid1");
    
    // 테이블 기반 답변형
    mav.setViewName("/contents/list_by_cateno_search_paging_img_table3");
    
    return mav;
  }    
 
  // http://localhost:9090/resort/contents/reply.do?cateno=1&contentsno=1
  /**
   * 답변 폼
   * @return
   */
  @RequestMapping(value="/contents/reply.do", method=RequestMethod.GET )
  public ModelAndView reply(int cateno, int contentsno) {
    ModelAndView mav = new ModelAndView();
    System.out.println("답변 대상: " + contentsno);
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO);

    mav.setViewName("/contents/reply"); // webapp/contents/reply.jsp
    
    return mav;
  }

  // http://localhost:9090/resort/contents/reply.do
  /**
   * 답변 등록 처리
   * @return
   */
  @RequestMapping(value="/contents/reply.do", 
                              method=RequestMethod.POST )
  public ModelAndView reply(HttpServletRequest request, ContentsVO contentsVO) {
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String file1 = "";     // main image
    String thumb1 = ""; // preview image
        
    String upDir = Tool.getRealPath(request, "/contents/storage/main_images"); // 절대 경로
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    MultipartFile mf = contentsVO.getFile1MF();
    long size1 = mf.getSize();  // 파일 크기
    if (size1 > 0) { // 파일 크기 체크
      // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 120, height: 80
        thumb1 = Tool.preview(upDir, file1, 250, 200); 
      }
      
    }    
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    contentsVO.setIp(request.getRemoteAddr()); // 접속자 IP

    // PK return 됨
    contentsVO.setFile1(file1);
    contentsVO.setThumb1(thumb1);
    contentsVO.setSize1(size1);
    
     // --------------------------- 답변 관련 코드 시작 --------------------------
     // System.out.println("댓글을 붙일 부모글 번호: " + contentsVO.getContentsno());
     ContentsVO parentVO = contentsProc.read(contentsVO.getContentsno()); // 부모글 정보 추출
     
     HashMap<String, Object> map = new HashMap<String, Object>();
     map.put("grpno", parentVO.getGrpno());
     map.put("ansnum",  parentVO.getAnsnum());
     contentsProc.increaseAnsnum(map); // 현재 등록된 답변 뒤로 +1 처리함.

     contentsVO.setGrpno(parentVO.getGrpno()); // 부모의 그룹번호 할당
     contentsVO.setIndent(parentVO.getIndent() + 1); // 답변 차수 증가
     contentsVO.setAnsnum(parentVO.getAnsnum() + 1); // 부모 바로 아래 등록
     // --------------------------- 답변 관련 코드 종료 --------------------------
    
    contentsVO.setIp(request.getRemoteAddr()); // 접속자 IP
    int cnt = this.contentsProc.reply(contentsVO); // Call By Reference로 전송
    
    System.out.println("cnt: " + cnt);
    mav.addObject("cnt", cnt);
    
    // ---------------------------------------------------------------------------------------
    // cnt, contentsno return 
    // ---------------------------------------------------------------------------------------
    // Spring <-----> contentsVO <-----> MyBATIS
    // Spring과 MyBATIS가 ContentsVO를 공유하고 있음.
    // MyBATIS는 insert후 PK 컬럼인 contentsno변수에 새로 생성된 PK를 할당함.
    int contentsno = contentsVO.getContentsno();  // MyBATIS 리턴된 PK
    System.out.println("contentsno: " + contentsno);
    mav.addObject("contentsno", contentsno); // request에 저장
    // ---------------------------------------------------------------------------------------
    
    mav.addObject("cateno", contentsVO.getCateno());
    mav.addObject("url", "reply_msg"); // // webapp/contents/reply_msg.jsp
    
    if (cnt == 1) {  // 정상적으로 글이 등록된 경우만 글 수 증가
      this.cateProc.increaseCnt(contentsVO.getCateno()); // 글수 증가
    }
    /*
     * // mav.setViewName("/contents/create_msg"); //
     * mav.setViewName("redirect:/contents/list.do"); // spring 재호출
     * mav.setViewName("redirect:/contents/msg.do"); } else { //
     * mav.setViewName("/contents/create_msg"); // webapp/contents/create_msg.jsp }
     */    
    
    mav.setViewName("redirect:/contents/msg.do");
    
    return mav;
  }

}




