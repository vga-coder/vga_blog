package mvc.vga.reply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mvc.vga.admin.AdminProcInter;
import mvc.vga.categrp.CategrpProcInter;
import mvc.vga.mem.MemVO;

@Controller
public class ReplyCont {
  @Autowired
  @Qualifier("mvc.vga.reply.ReplyProc") // �̸� ����
  private ReplyProcInter replyProc;
  
  @Autowired
  @Qualifier("mvc.vga.admin.AdminProc") // �̸� ����
  private AdminProcInter adminProc;
  
  public ReplyCont(){
    System.out.println("--> ReplyCont created.");
  }
  
  /**
   * ��� ��� ó��
   * @param replyVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/create.do",
                            method = RequestMethod.POST,
                            produces = "text/plain;charset=UTF-8")
  public String create(ReplyVO replyVO) {
    int count = replyProc.create(replyVO);  // Call By Reference
    int replyno = replyVO.getReplyno();  // Call By Reference������ PK ���� ����
    System.out.println("replyno: " + replyno);
    
    JSONObject obj = new JSONObject();
    obj.put("count",count);
    obj.put("replyno",replyno); // ��ϵ� ���� PK ����
    // System.out.println("��ϵ� replyno: " +replyno );
   
    ReplyMemberVO replyMemberVO = this.replyProc.read(replyno);
    
    // Ű�� ���� ������ �ڵ����� �����ϱ����Ͽ� ��ü�� 1���̳� ArrayList�� ������.
    ArrayList<ReplyMemberVO> list = new ArrayList<ReplyMemberVO>();
    list.add(replyMemberVO);
    
    obj.put("replyMemberVO", list); // Ű�� ���� ������ �ڵ����� ������.
    // {"replyno":136,"count":1,"replyMemberVO":[{"memberno":18,"rdate":"2020-07-10 15:44:19","passwd":"19","replyno":136,"id":"user1","content":"19","contentsno":31}]}
    System.out.println(obj.toString());
    return obj.toString();     
    
  }
  
  /**
   * ��� ��ü ���, �����ڸ� ��ȸ ����
   * @param session
   * @return
   */
  @RequestMapping(value="/reply/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (adminProc.isAdmin(session)) {
      List<ReplyVO> list = replyProc.list();
      
      mav.addObject("list", list);
      mav.setViewName("/reply/list"); // /webapp/reply/list.jsp

    } else {
      mav.setViewName("redirect:/admin/login_need.jsp"); // /webapp/admin/login_need.jsp
    }
    
    return mav;
  }

  /**
   ���� ��� ���
   http://localhost:9090/ojt/reply/list_by_contentsno.do?contentsno=1
   * @param contentsno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/list_by_contentsno.do",
                            method = RequestMethod.GET,
                            produces = "text/plain;charset=UTF-8")
  public String list_by_contentsno(int contentsno) {
    List<ReplyVO> list = replyProc.list_by_contentsno(contentsno);
    
    JSONObject obj = new JSONObject();
    obj.put("list", list);
 
    return obj.toString(); 

  }
  
  /**
   * ���
   * http://localhost:9090/resort/reply/list_by_contentsno_join.do?contentsno=31
   * @param contentsno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/list_by_contentsno_join.do",
                              method = RequestMethod.GET,
                              produces = "text/plain;charset=UTF-8")
  public String list_by_contentsno_join(int contentsno) {
    // String msg="JSON ���";
    // return msg;
    
    List<ReplyMemberVO> list = replyProc.list_by_contentsno_join(contentsno);
    
    JSONObject obj = new JSONObject();
    obj.put("list", list);
 
    return obj.toString();     
  }

  /**
   * http://localhost:9090/resort/reply/delete.do?replyno=1&passwd=1234
   * {"delete_count":0,"count":0}
   * {"delete_count":1,"count":1}
   * @param replyno
   * @param passwd
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/delete.do", 
                              method = RequestMethod.POST,
                              produces = "text/plain;charset=UTF-8")
  public String delete(int replyno, String passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("replyno", replyno);
    map.put("passwd", passwd);
    
    int count = replyProc.checkPasswd(map); // �н����� ��ġ ����, 1: ��ġ, 0: ����ġ
    int delete_count = 0;                              // ������ ���
    if (count == 1) {
      delete_count = replyProc.delete(replyno); // ��� ����
    }
    
    JSONObject obj = new JSONObject();
    obj.put("count", count); // �н����� ��ġ ����, 1: ��ġ, 0: ����ġ
    obj.put("delete_count", delete_count); // ������ ���
    
    return obj.toString();
  }

  /**
   * ������ ��ư ����¡ ���
   * http://localhost:9090/resort/reply/list_by_contentsno_join_add_view.do?contentsno=31&replyPage=1
   * @param contentsno ��� �θ�� ��ȣ
   * @param replyPage ��� ������
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/list_by_contentsno_join_add_view.do",
                              method = RequestMethod.GET,
                              produces = "text/plain;charset=UTF-8")
  public String list_by_contentsno_join(int contentsno, int replyPage) {
//    System.out.println("contentsno: " + contentsno);
//    System.out.println("replyPage: " + replyPage);
    
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("contentsno", contentsno); 
    map.put("replyPage", replyPage);    
    
    List<ReplyMemberVO> list = replyProc.list_by_contentsno_join_add_view(map);
    
    JSONObject obj = new JSONObject();
    obj.put("list", list);
 
    return obj.toString();     
  }
  
  /**
   * ��ȸ
   * http://localhost:9090/resort/reply/read.do?replyno=108 
   * @param replyVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reply/read.do",
                            method = RequestMethod.GET,
                            produces = "text/plain;charset=UTF-8")
  public String read(int replyno) {
    System.out.println("replyno: " + replyno);
  
    ReplyMemberVO replyMemberVO = this.replyProc.read(replyno);
    
    // JSON ����� �ܼ�ȭ�ϱ����� ��ȯ
    ArrayList<ReplyMemberVO> list = new ArrayList<ReplyMemberVO>();
    list.add(replyMemberVO);
    
    JSONObject obj = new JSONObject();
    obj.put("replyMemberVO", list);
    // {"replyMemberVO":[{"memberno":18,"rdate":"2020-07-10 12:52:41","passwd":"10","replyno":108,"id":"user1","content":"10","contentsno":31}]}
    return obj.toString();     
  }
}


