package mvc.vga.categrp;
 
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mvc.vga.admin.AdminProcInter;

@Controller
public class CategrpCont {
  @Autowired
  @Qualifier("mvc.vga.admin.AdminProc")
  private AdminProcInter adminProc;
  
  @Autowired
  @Qualifier("mvc.vga.categrp.CategrpProc")
  private CategrpProcInter categrpProc;
  
  public CategrpCont() {
    System.out.println("--> CategrpCont created.");
  }
  
  // http://localhost:9090/resort/categrp/create.do
  /**
   * ��� ��
   * @return
   */
  @RequestMapping(value="/categrp/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/categrp/create"); // webapp/categrp/create.jsp
    
    return mav; // forward
  }
  
  // http://localhost:9090/resort/categrp/create.do
  /**
   * ��� ó��
   * @param categrpVO
   * @return
   */
  @RequestMapping(value="/categrp/create.do", method=RequestMethod.POST )
  public ModelAndView create(CategrpVO categrpVO) {
    // CategrpVO categrpVO <FORM> �±��� ������ �ڵ� ������.
    // request.setAttribute("categrpVO", categrpVO); �ڵ� ����
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.categrpProc.create(categrpVO); // ��� ó��
    mav.addObject("cnt", cnt); // request�� ����, request.setAttribute("cnt", cnt)
    
    mav.setViewName("/categrp/create_msg"); // webapp/categrp/create_msg.jsp

    return mav; // forward
  }
  
  // http://localhost:9090/resort/categrp/list.do
  /**
   * �����ڸ� ��ȸ������ ��ü ���
   * @return
   */
@RequestMapping(value="/categrp/list.do", method=RequestMethod.GET )
public ModelAndView list(HttpSession session) {
  ModelAndView mav = new ModelAndView();
  
  if (adminProc.isAdmin(session)) {
    // List<CategrpVO> list = this.categrpProc.list_categrpno_asc();
    List<CategrpVO> list = this.categrpProc.list_seqno_asc();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/categrp/list"); // webapp/categrp/list.jsp
  } else {
    mav.setViewName("/admin/login_need");  // /admin/login_need.jsp  
  }
  return mav;
}
  
//  @RequestMapping(value="/categrp/list.do", method=RequestMethod.GET )
//  public ModelAndView list() {
//    ModelAndView mav = new ModelAndView();
//    
//    // List<CategrpVO> list = this.categrpProc.list_categrpno_asc();
//    List<CategrpVO> list = this.categrpProc.list_seqno_asc();
//    mav.addObject("list", list); // request.setAttribute("list", list);
//
//    mav.setViewName("/categrp/list"); // webapp/categrp/list.jsp
//    return mav;
//  }

  // http://localhost:9090/resort/categrp/read_update.do
  /**
   * ��ȸ + ������
   * @param categrpno ��ȸ�� ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/categrp/read_update.do", method=RequestMethod.GET )
  public ModelAndView read_update(int categrpno) {
    // request.setAttribute("categrpno", int categrpno) �۵� �ȵ�.
    
    ModelAndView mav = new ModelAndView();
    
    CategrpVO categrpVO = this.categrpProc.read(categrpno);
    mav.addObject("categrpVO", categrpVO);  // request ��ü�� ����
    
    List<CategrpVO> list = this.categrpProc.list_categrpno_asc();
    mav.addObject("list", list);  // request ��ü�� ����

    mav.setViewName("/categrp/read_update"); // webapp/categrp/read_update.jsp
    return mav; // forward
  }
  
  // http://localhost:9090/resort/categrp/update.do
  /**
   * ���� ó��
   * @param categrpVO
   * @return
   */
  @RequestMapping(value="/categrp/update.do", method=RequestMethod.POST )
  public ModelAndView update(CategrpVO categrpVO) {
    // CategrpVO categrpVO <FORM> �±��� ������ �ڵ� ������.
    // request.setAttribute("categrpVO", categrpVO); �ڵ� ����
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.categrpProc.update(categrpVO);
    mav.addObject("cnt", cnt); // request�� ����
    
    mav.setViewName("/categrp/update_msg"); // webapp/categrp/update_msg.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/categrp/read_delete.do
  /**
   * ��ȸ + ������
   * @param categrpno ��ȸ�� ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/categrp/read_delete.do", method=RequestMethod.GET )
  public ModelAndView read_delete(int categrpno) {
    ModelAndView mav = new ModelAndView();
    
    CategrpVO categrpVO = this.categrpProc.read(categrpno); // ������ �ڷ� �б�
    mav.addObject("categrpVO", categrpVO);  // request ��ü�� ����
    
    List<CategrpVO> list = this.categrpProc.list_categrpno_asc(); // ���
    mav.addObject("list", list);  // request ��ü�� ����

    mav.setViewName("/categrp/read_delete"); // webapp/categrp/read_delete.jsp
    return mav;
  }

  // http://localhost:9090/resort/categrp/delete.do
  /**
   * ����
   * @param categrpno ��ȸ�� ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/categrp/delete.do", method=RequestMethod.POST )
  public ModelAndView delete(int categrpno) {
    ModelAndView mav = new ModelAndView();
    
    CategrpVO categrpVO = this.categrpProc.read(categrpno); // ���� ����
    mav.addObject("categrpVO", categrpVO);  // request ��ü�� ����
    
    int cnt = this.categrpProc.delete(categrpno); // ���� ó��
    mav.addObject("cnt", cnt);  // request ��ü�� ����
    
    mav.setViewName("/categrp/delete_msg"); // webapp/categrp/delete_msg.jsp

    return mav;
  }
  
  // http://localhost:9090/resort/categrp/update_seqno_up.do?categrpno=1
  // http://localhost:9090/resort/categrp/update_seqno_up.do?categrpno=1000
  /**
   * �켱���� ���� up 10 �� 1
   * @param categrpno ��ȸ�� ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/categrp/update_seqno_up.do", 
                              method=RequestMethod.GET )
  public ModelAndView update_seqno_up(int categrpno) {
    ModelAndView mav = new ModelAndView();
    
    CategrpVO categrpVO = this.categrpProc.read(categrpno); // ī�װ� �׷� ����
    mav.addObject("categrpVO", categrpVO);  // request ��ü�� ����
    
    int cnt = this.categrpProc.update_seqno_up(categrpno);  // �켱 ���� ���� ó��
    mav.addObject("cnt", cnt);  // request ��ü�� ����

    mav.setViewName("/categrp/update_seqno_up_msg"); // update_seqno_up_msg.jsp
    return mav;
  }  
  
  // http://localhost:9090/resort/categrp/update_seqno_down.do?categrpno=1
  // http://localhost:9090/resort/categrp/update_seqno_down.do?categrpno=1000
  /**
   * �켱���� ���� up 10 �� 1
   * @param categrpno ��ȸ�� ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/categrp/update_seqno_down.do", 
                              method=RequestMethod.GET )
  public ModelAndView update_seqno_down(int categrpno) {
    ModelAndView mav = new ModelAndView();
    
    CategrpVO categrpVO = this.categrpProc.read(categrpno); // ī�װ� �׷� ����
    mav.addObject("categrpVO", categrpVO);  // request ��ü�� ����
    
    int cnt = this.categrpProc.update_seqno_down(categrpno);
    mav.addObject("cnt", cnt);  // request ��ü�� ����

    mav.setViewName("/categrp/update_seqno_down_msg"); // update_seqno_down_msg.jsp

    return mav;
  }  
  
  /**
   * ��� ����� ����
   * @param categrpVO
   * @return
   */
  @RequestMapping(value="/categrp/update_visible.do", 
      method=RequestMethod.GET )
  public ModelAndView update_visible(CategrpVO categrpVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.categrpProc.update_visible(categrpVO);
    
    mav.setViewName("redirect:/categrp/list.do"); // request ��ü�� ������ �ȵ�. 
    
    return mav;
  }  
  
}









