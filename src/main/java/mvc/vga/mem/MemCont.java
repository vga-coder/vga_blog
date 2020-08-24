package mvc.vga.mem;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import mvc.vga.tool.Tool;
import mvc.vga.tool.Upload;




@Controller
public class MemCont {
	@Autowired
	@Qualifier("mvc.vga.mem.MemProc")
	private MemProcInter memProc = null;
	
	public MemCont() {
		System.out.println("---> MemCont created.");
	}	
	
	  /**
	   * 메시지
	   * @param memberno
	   * @return
	   */
	  @RequestMapping(value="/mem/msg.do", method=RequestMethod.GET)
	  public ModelAndView delete_msg(String url){
	    ModelAndView mav = new ModelAndView();
	    
	    mav.setViewName("/mem/" + url); // forward
	    
	    return mav; 
	  }
	  
	  // http://localhost:9090/team4/mem/create.do
	  /**
	   * 등록 폼
	   * @return
	   */
	  @RequestMapping(value="/mem/create.do", method=RequestMethod.GET )
	  public ModelAndView create() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/mem/create"); 
	    
	    return mav;
	  }
	  
	  // http://localhost:9090/team4/mem/checkID.do?id=user1
	  /**
	   * ID 중복 체크, JSON 출력
	   * @return
	   */
	  @ResponseBody
	  @RequestMapping(value="/mem/checkID.do", method=RequestMethod.GET ,
	                              produces = "text/plain;charset=UTF-8" )
	  public String checkID(String mem_id) {
	    int cnt = this.memProc.checkID(mem_id);
	    
	    JSONObject json = new JSONObject();
	    json.put("cnt", cnt);
	    
	    return json.toString(); 
	  }
	  
	  /**
	   * 등록 처리
	   * @param memberVO
	   * @return
	   */
	  @RequestMapping(value="/mem/create.do", method=RequestMethod.POST)
	  public ModelAndView create(MemVO memVO){
	    ModelAndView mav = new ModelAndView();
	   	    
	    int cnt= memProc.create(memVO);
	    mav.addObject("cnt", cnt); // redirect parameter 적용
	    mav.addObject("url", "create_msg"); // create_msg.jsp, redirect parameter 적용
	    
	    mav.setViewName("redirect:/mem/msg.do");
	    
	    return mav;
	  }
	  
	  // http://localhost:9090/team4/mem/list.do
	  /**
	   * 전체 목록
	   * @return
	   */
	  @RequestMapping(value="/mem/list.do", method=RequestMethod.GET )
	  public ModelAndView list() {
	    ModelAndView mav = new ModelAndView();
	    
	    List<MemVO> list = this.memProc.list();
	    mav.addObject("list", list);
	    
	    mav.setViewName("/mem/list"); 
	    return mav;
	  }
	  
	  // http://localhost:9090/teat4/mem/delete.do
	  /**
	   * 삭제 폼
	   * @param cateVO
	   * @return
	   */
	  @RequestMapping(value="/mem/delete.do", method=RequestMethod.GET )
	  public ModelAndView delete(int memno) {
		  ModelAndView mav = new ModelAndView();
		    
		  MemVO memVO = this.memProc.read(memno);
   	      mav.addObject("memVO", memVO);  // request 객체에 저장

   	      List<MemVO> list = this.memProc.list();
   	      mav.addObject("list", list);

		  mav.setViewName("/mem/drop"); 
		  
		  return mav;
	  }
	  
	  // http://localhost:9090/teat4/mem/delete.do
	  /**
	   * 삭제 처리
	   * @param cateVO
	   * @return
	   */
	  @RequestMapping(value="/mem/delete.do", method=RequestMethod.POST )
	  public ModelAndView delete_proc(int memno) {
	    ModelAndView mav = new ModelAndView();
	    
	    int cnt = this.memProc.delete(memno);
	    mav.addObject("cnt", cnt);

        mav.setViewName("redirect:/index.jsp"); 
	    
	    return mav;
	  }
	  
	  /**
	   * 로그인 폼
	   * @param memno
	   * @return
	   */
	  @RequestMapping(value="/mem/login.do", method=RequestMethod.GET)
	  public ModelAndView login(HttpServletRequest request){
	    ModelAndView mav = new ModelAndView();
	    
	    Cookie[] cookies = request.getCookies();
	    Cookie cookie = null;

	    String ck_id = ""; // id 저장
	    String ck_id_save = ""; // id 저장 여부를 체크
	    String ck_passwd = ""; // passwd 저장
	    String ck_passwd_save = ""; // passwd 저장 여부를 체크

	    if (cookies != null) {
	      for (int i=0; i < cookies.length; i++){
	        cookie = cookies[i]; // 쿠키 객체 추출
	        
	        if (cookie.getName().equals("ck_id")){
	          ck_id = cookie.getValue(); 
	        }else if(cookie.getName().equals("ck_id_save")){
	          ck_id_save = cookie.getValue();  // Y, N
	        }else if (cookie.getName().equals("ck_passwd")){
	          ck_passwd = cookie.getValue();         // 1234
	        }else if(cookie.getName().equals("ck_passwd_save")){
	          ck_passwd_save = cookie.getValue();  // Y, N
	        }
	      }
	    }
	    
	    mav.addObject("ck_id", ck_id); 
	    mav.addObject("ck_id_save", ck_id_save);
	    mav.addObject("ck_passwd", ck_passwd);
	    mav.addObject("ck_passwd_save", ck_passwd_save);
	    
	    mav.setViewName("/mem/login");
	    
	    return mav;
	  }
	  

	  /**
	   * 로그인 처리
	   * @param mem_id
	   * @param mem_pw
	   * @return
	   */
	  @RequestMapping(value="/mem/login.do", method=RequestMethod.POST)
	  public ModelAndView login_proc(HttpServletRequest request,
              HttpServletResponse response,
              HttpSession session,
              String mem_id, String mem_pw,
              @RequestParam(value="id_save", defaultValue="") String id_save,
              @RequestParam(value="passwd_save", defaultValue="") String passwd_save){
	    
		ModelAndView mav = new ModelAndView();
	    
	    HashMap<Object, Object> map = new HashMap<Object, Object>();
	    map.put("mem_id", mem_id);
	    map.put("mem_pw", mem_pw);
	    
	    int cnt = this.memProc.login(map);
	    

    	
	    if (cnt == 1) { // 현재 패스워드가 일치하는 경우
	    	MemVO memVO = this.memProc.readById(mem_id);
	    	mav.addObject("memVO", memVO);
	    	
	    	session.setAttribute("memno", memVO.getMemno());
	        session.setAttribute("mem_id", mem_id);
	        session.setAttribute("memVO", memVO);
	        
	     // -------------------------------------------------------------------
	        // id 관련 쿠기 저장
	        // -------------------------------------------------------------------
	        if (id_save.equals("Y")) { // id를 저장할 경우
	          Cookie ck_id = new Cookie("ck_id", mem_id);
	          ck_id.setMaxAge(60 * 60 * 72 * 10); // 30 day, 초단위
	          response.addCookie(ck_id);
	        } else { // N, id를 저장하지 않는 경우
	          Cookie ck_id = new Cookie("ck_id", "");
	          ck_id.setMaxAge(0);
	          response.addCookie(ck_id);
	        }
	        // id를 저장할지 선택하는  CheckBox 체크 여부
	        Cookie ck_id_save = new Cookie("ck_id_save", id_save);
	        ck_id_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
	        response.addCookie(ck_id_save);
	        // -------------------------------------------------------------------

	        // -------------------------------------------------------------------
	        // Password 관련 쿠기 저장
	        // -------------------------------------------------------------------
	        if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
	          Cookie ck_passwd = new Cookie("ck_passwd", mem_pw);
	          ck_passwd.setMaxAge(60 * 60 * 72 * 10); // 30 day
	          response.addCookie(ck_passwd);
	        } else { // N, 패스워드를 저장하지 않을 경우
	          Cookie ck_passwd = new Cookie("ck_passwd", "");
	          ck_passwd.setMaxAge(0);
	          response.addCookie(ck_passwd);
	        }
	        // passwd를 저장할지 선택하는  CheckBox 체크 여부
	        Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
	        ck_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
	        response.addCookie(ck_passwd_save);
	        // -------------------------------------------------------------------
		    mav.setViewName("/mem/mypage");    
	    } else {
	    	mav.setViewName("/mem/login_fail");
	    }

	    return mav;
	  }

	  /**
	   * 로그아웃 처리
	   * @param session
	   * @return
	   */
	  @RequestMapping(value="/mem/logout.do", 
	                             method=RequestMethod.GET)
	  public ModelAndView logout(HttpSession session){
	    ModelAndView mav = new ModelAndView();
	    session.invalidate(); // 모든 session 변수 삭제
	    
	    mav.setViewName("redirect:/mem/logout_msg.jsp");
	    
	    return mav;
	  }
	  
	  // http://localhost:9090/team4/mem/passwd_update.do
	  /**
	   * 패스워드 변경 폼
	   * @return
	   */
	  @RequestMapping(value="/mem/passwd_update.do", method=RequestMethod.GET )
	  public ModelAndView passwd_update() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/mem/passwd_update"); 
	    
	    return mav;
	  }
	  
	  /**
	   * 패스워드 변경 처리
	   * @param memno 회원 번호
	   * @param current_passwd 현재 패스워드
	   * @param new_passwd 새로운 패스워드
	   * @return
	   */
	  @RequestMapping(value="/mem/passwd_update.do", method=RequestMethod.POST)
	  public ModelAndView passwd_update(int memno, String current_passwd, String new_passwd){
	    ModelAndView mav = new ModelAndView();
	    
	    // 현재 패스워드 검사
	    HashMap<Object, Object> map = new HashMap<Object, Object>();
	    map.put("memno", memno);
	    map.put("mem_pw", current_passwd);
	    
	    int cnt = memProc.passwd_check(map);
	    
	    if (cnt == 1) { // 현재 패스워드가 일치하는 경우
	      map.put("mem_pw", new_passwd);
	      memProc.passwd_update(map); // 패스워드 변경
	      mav.addObject("memno", memno);
	      

	    }

	    MemVO memVO = this.memProc.read(memno);
	    mav.addObject("memVO", memVO);
	    
	    mav.setViewName("/mem/mypage");
	    
	    return mav;
	  }
	  
	  // http://localhost:9090/team4/mem/mypage.do
	  /**
	   * 내 정보 폼
	   * @return
	   */
	  @RequestMapping(value="/mem/mypage.do", method=RequestMethod.GET )
	  public ModelAndView mypage(int memno) {
	    ModelAndView mav = new ModelAndView();
	    
	    MemVO memVO = this.memProc.read(memno);
	    mav.addObject("memVO", memVO);
	    mav.addObject("memno", memVO.getMemno());
	    mav.setViewName("/mem/mypage");
	    
	    return mav;
	  }
	  
	  // http://localhost:9090/team4/mem/contact_update.do
	  /**
	   * 연락처 변경 폼
	   * @return
	   */
	  @RequestMapping(value="/mem/contact_update.do", method=RequestMethod.GET )
	  public ModelAndView contact_update(int memno) {
	    ModelAndView mav = new ModelAndView();
	    
	    MemVO memVO = this.memProc.read(memno);
	    mav.addObject("memVO", memVO);
	    mav.addObject("memno", memVO.getMemno());
	    
	    mav.setViewName("/mem/contact_update"); 
	    
	    return mav;
	  }
	  
	  /**
	   * 연락처 변경 처리
	   * @param memVO
	   * @return
	   */
	  @RequestMapping(value="/mem/contact_update.do", method=RequestMethod.POST)
	  public ModelAndView contact_update_proc(MemVO memVO){
	    ModelAndView mav = new ModelAndView();
	    
	    memProc.contact_update(memVO);
	    
	    memVO = this.memProc.read(memVO.getMemno());
	    mav.addObject("memVO", memVO);
	    mav.addObject("memno", memVO.getMemno()); // redirect parameter 적용

	    mav.setViewName("mem/mypage");
	    
	    return mav;
	  }
	  
	  /**
	   * 사진 수정 폼
	   * @return
	   */
	  @RequestMapping(value="/mem/mem_img_update.do", method=RequestMethod.GET )
	  public ModelAndView mem_img_update(int memno) {
	    ModelAndView mav = new ModelAndView();
	    
	    MemVO memVO = this.memProc.read(memno);
	    mav.addObject("memVO", memVO);
	    mav.addObject("memno", memVO.getMemno());
	    
	    mav.setViewName("/mem/mem_img_update"); 
	    
	    return mav;
	  }
	  
	  // http://localhost:9090/team4/mem/mem_img_update.do
	  /**
	   * 사진 수정 처리
	   * @param cateVO
	   * @return
	   */
	  @RequestMapping(value="/mem/mem_img_update.do", 
	                              method=RequestMethod.POST )
	  public ModelAndView create(HttpServletRequest request, MemVO memVO) {
	    ModelAndView mav = new ModelAndView();
	    // -------------------------------------------------------------------
	    // 파일 전송 코드 시작
	    // -------------------------------------------------------------------
	    String mem_img = "";     // main image
	        
	    String upDir = Tool.getRealPath(request, "/mem/img"); // 절대 경로
	    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
	    MultipartFile mf = memVO.getMem_imgMF();

	    mem_img = Upload.saveFileSpring(mf, upDir); 

	    mem_img = Tool.preview(upDir, mem_img, 150, 100); 
  
	    // -------------------------------------------------------------------
	    // 파일 전송 코드 종료
	    // -------------------------------------------------------------------
	    
	    
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
	    memVO.setMem_img(mem_img);
	    int cnt = this.memProc.mem_img_update(memVO);
	    System.out.println("cnt: " + cnt);
	    mav.addObject("cnt", cnt);
	    
	    // ---------------------------------------------------------------------------------------
	    // cnt, contentsno return 
	    // ---------------------------------------------------------------------------------------
	    // Spring <-----> contentsVO <-----> MyBATIS
	    // Spring과 MyBATIS가 ContentsVO를 공유하고 있음.
	    // MyBATIS는 insert후 PK 컬럼인 contentsno변수에 새로 생성된 PK를 할당함.
	    int memno = memVO.getMemno();  // MyBATIS 리턴된 PK
	    System.out.println("memno: " + memno);
	    mav.addObject("memno", memno); // request에 저장
	    // ---------------------------------------------------------------------------------------
	    /*
	     * // mav.setViewName("/contents/create_msg"); //
	     * mav.setViewName("redirect:/contents/list.do"); // spring 재호출
	     * mav.setViewName("redirect:/contents/msg.do"); } else { //
	     * mav.setViewName("/contents/create_msg"); // webapp/contents/create_msg.jsp }
	     */    
	    
	    mav.setViewName("redirect:/mem/mypage.do");
	    
	    return mav;
	  }
}
