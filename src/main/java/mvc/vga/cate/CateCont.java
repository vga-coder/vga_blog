package mvc.vga.cate;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mvc.vga.categrp.CategrpProcInter;
import mvc.vga.categrp.CategrpVO;
import mvc.vga.contents.ContentsProcInter;

@Controller
public class CateCont {
  @Autowired
  @Qualifier("mvc.vga.categrp.CategrpProc")
  private CategrpProcInter categrpProc;

  @Autowired
  @Qualifier("mvc.vga.cate.CateProc")
  private CateProcInter cateProc;

  @Autowired
  @Qualifier("mvc.vga.contents.ContentsProc")
  private ContentsProcInter contentsProc;
  
  public CateCont() {
    System.out.println("--> CateCont created.");
  }
  
  // http://localhost:9090/resort/cate/create.do
  /**
   * ��� ��
   * @return
   */
  @RequestMapping(value="/cate/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cate/create"); // webapp/cate/create.jsp
    
    return mav;
  }
  
  // http://localhost:9090/resort/cate/create.do
  /**
   * ��� ó��
   * @param cateVO
   * @return
   */
  @RequestMapping(value="/cate/create.do", method=RequestMethod.POST )
  public ModelAndView create(CateVO cateVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.create(cateVO);
    mav.addObject("cnt", cnt); // request�� ����
    
    CategrpVO categrpVO = this.categrpProc.read(cateVO.getCategrpno());
    mav.addObject("categrpVO", categrpVO);
    
    if (cnt == 1) {
      mav.setViewName("/cate/create_msg"); // webapp/cate/create_msg.jsp
      // mav.setViewName("redirect:/cate/list.do"); // spring ��ȣ��
    } else { 
      mav.setViewName("/cate/create_msg"); // webapp/cate/create_msg.jsp
    }
    
    return mav;
  }
  
  // http://localhost:9090/resort/cate/list.do
  /**
   * ��ü ���
   * @return
   */
  @RequestMapping(value="/cate/list.do", method=RequestMethod.GET )
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    List<CateVO> list = this.cateProc.list_seqno_asc();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/cate/list"); // webapp/cate/list.jsp
    return mav;
  }

  // http://localhost:9090/resort/cate/list_join.do
  /**
   * categrp + cate join ��ü ���
   * @return
   */
  @RequestMapping(value="/cate/list_join.do", method=RequestMethod.GET )
  public ModelAndView list_join() {
    ModelAndView mav = new ModelAndView();
    
    List<Categrp_Cate_join> list = this.cateProc.list_join();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/cate/list_join"); // webapp/cate/list_join.jsp
    return mav;
  }
  
  // http://localhost:9090/resort/cate/list_join_by_categrpno.do
  /**
   * categrp + cate join ��ü ���
   * @return
   */
  @RequestMapping(value="/cate/list_join_by_categrpno.do", method=RequestMethod.GET )
  public ModelAndView list_join_by_categrpno(int categrpno) {
    ModelAndView mav = new ModelAndView();
    
    CategrpVO categrpVO = this.categrpProc.read(categrpno);
    mav.addObject("categrpVO", categrpVO);
    
    List<Categrp_Cate_join> list = this.cateProc.list_join_by_categrpno(categrpno);
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/cate/list_join_by_categrpno"); // webapp/cate/list_join_by_categrpno.jsp
    return mav;
  }
  
  // http://localhost:9090/resort/cate/list_all.do
  /**
   * categrp + cate join ��ü ���
   * @return
   */
  @RequestMapping(value="/cate/list_all.do", method=RequestMethod.GET )
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    List<Categrp_Cate_VO> list = this.cateProc.list_all();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/cate/list_all"); // webapp/cate/list_all.jsp
    return mav;
  }

  // http://localhost:9090/resort/cate/list_by_categrpno.do
  /**
   * categrp + cate join categrpno �� ���
   * @return
   */
  @RequestMapping(value="/cate/list_by_categrpno.do", method=RequestMethod.GET )
  public ModelAndView list_by_categrpno(int categrpno) {
    ModelAndView mav = new ModelAndView();
    
    // List<Categrp_Cate_VO_list> �ƴ�
    Categrp_Cate_VO_list list = this.cateProc.list_by_categrpno(categrpno);
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/cate/list_by_categrpno"); // webapp/cate/list_by_categrpno.jsp
    return mav;
  }
  
  // http://localhost:9090/resort/cate/read_update.do
  /**
   * ��ȸ + ������
   * @param cateno ��ȸ�� ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/cate/read_update.do", method=RequestMethod.GET )
  public ModelAndView read_update(int cateno) {
    // request.setAttribute("cateno", int cateno) �۵� �ȵ�.
    
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);  // request ��ü�� ����
    
    List<CateVO> list = this.cateProc.list_seqno_asc();
    mav.addObject("list", list);  // request ��ü�� ����

    mav.setViewName("/cate/read_update"); // webapp/cate/read_update.jsp
    return mav; // forward
  }
  
  // http://localhost:9090/resort/cate/update.do
  /**
   * ���� ó��
   * @param cateVO
   * @return
   */
  @RequestMapping(value="/cate/update.do", method=RequestMethod.POST )
  public ModelAndView update(CateVO cateVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.update(cateVO);
    mav.addObject("cnt", cnt); // request�� ����
    
    if (cnt == 1) {
      // mav.setViewName("/cate/update_msg"); // webapp/cate/update_msg.jsp
      mav.setViewName("redirect:/cate/list_all.do"); // spring ��ȣ��
    } else { 
      mav.setViewName("/cate/update_msg"); // webapp/cate/update_msg.jsp
    }
    
    return mav;
  }
  
  // http://localhost:9090/resort/cate/read_delete.do
  /**
   * ��ȸ + ������
   * @param cateno ������ ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/cate/read_delete.do", method=RequestMethod.GET )
  public ModelAndView read_delete(int cateno) {
    // request.setAttribute("cateno", int cateno) �۵� �ȵ�.
    
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);  // request ��ü�� ����
    
    List<CateVO> list = this.cateProc.list_seqno_asc();
    mav.addObject("list", list);  // request ��ü�� ����

    mav.setViewName("/cate/read_delete"); // webapp/cate/read_delete.jsp
    return mav; // forward
  }
  
  // http://localhost:9090/resort/cate/delete.do
  /**
   * ���� ó��
   * @param cateVO
   * @return
   */
  @RequestMapping(value="/cate/delete.do", method=RequestMethod.POST )
  public ModelAndView delete(int cateno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.delete(cateno);
    mav.addObject("cnt", cnt); // request�� ����
    
    if (cnt == 1) {
      // mav.setViewName("/cate/delete_msg"); // webapp/cate/delete_msg.jsp
      mav.setViewName("redirect:/cate/list_all.do"); // spring ��ȣ��
    } else { 
      mav.setViewName("/cate/delete_msg"); // webapp/cate/delete_msg.jsp
    }
    
    return mav;
  }
  
  // http://localhost:9090/resort/cate/update_seqno_up.do?cateno=1
  /**
   * �켱���� ���� up 10 �� 1
   * @param cateno ��ȸ�� ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/cate/update_seqno_up.do", 
                              method=RequestMethod.GET )
  public ModelAndView update_seqno_up(int cateno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.update_seqno_up(cateno);  // �켱 ���� ���� ó��

    mav.setViewName("redirect:/cate/list_all.do"); 
    return mav;
  }  

  // http://localhost:9090/resort/cate/update_seqno_down.do?cateno=1
  /**
   * �켱���� ���� up 1 �� 10
   * @param cateno ��ȸ�� ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/cate/update_seqno_down.do", 
                              method=RequestMethod.GET )
  public ModelAndView update_seqno_down(int cateno) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.update_seqno_down(cateno);  // �켱 ���� ���� ó��

    mav.setViewName("redirect:/cate/list_all.do"); 
    return mav;
  }  
  
  /**
   * ��� ����� ����
   * @param cateVO
   * @return
   */
  @RequestMapping(value="/cate/update_visible.do", 
      method=RequestMethod.GET )
  public ModelAndView update_visible(CateVO cateVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.update_visible(cateVO);
    
    if (cnt == 0) {
      mav.setViewName("redirect:/cate/list_all.do"); // request ��ü�� ������ �ȵ�. 
    } else {
      String name = this.cateProc.read(cateVO.getCateno()).getName();
      mav.addObject("name", name);
      mav.setViewName("/cate/update_visible_msg"); //  /cate/update_visible_msg.jsp
    }
    return mav;
  }  
  
  /**
   * ���յ� ī�װ� �׷캰 ī�װ� ���
   * http://localhost:9090/resort/cate/list_index_left.do
   * @param request
   * @return
   */
 @RequestMapping(value="/cate/list_index_left.do", method=RequestMethod.GET)
 public ModelAndView list_index_left(HttpServletRequest request){
   // System.out.println("--> list_index() GET called.");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/cate/list_index_left"); // webapp/cate/list_index_left.jsp
   
   List<CategrpVO> categrp_list = categrpProc.list_seqno_asc(); // ī�װ� �׷� ���
   
   // Categrp: name, Cate: name ���� ���
   ArrayList<String> name_title_list = new ArrayList<String>();   
   
   StringBuffer url = new StringBuffer(); // ī�װ� ���� ��ũ ����
 
   // ī�װ� �׷� ���� ��ŭ ��ȯ
   for (int index = 0; index < categrp_list.size(); index++) {
     CategrpVO categrpVO = categrp_list.get(index); // �ϳ��� ī�װ� �׷� ����
 
     name_title_list.add("<LI class='categrp_name'>"+ categrpVO.getName() + "</LI>");

     // ī�װ� Join ���
     List<Categrp_Cate_join> cate_list = cateProc.list_join_by_categrpno(categrpVO.getCategrpno()); 
     
     // ī�װ� ������ŭ ��ȯ
     for (int j=0; j < cate_list.size(); j++) {
       Categrp_Cate_join categrp_Cate_join = cate_list.get(j);
       
       String name = categrp_Cate_join.getName(); // ī�װ� �̸�
       int cnt = categrp_Cate_join.getCnt();
       
       url.append("<LI class='cate_name'>");
       url.append("  <A href='" + request.getContextPath()+ "/contents/list.do?cateno="+categrp_Cate_join.getCateno()+"&word='>");
       url.append(name);
       url.append("  </A>");
       url.append("  <span style='font-size: 0.9em; color: #555555;'>("+cnt+")</span>");
       url.append("</LI>");
       name_title_list.add(url.toString()); // ��� ��Ͽ� �ϳ��� cate �߰� 
       
       url.delete(0, url.toString().length()); // ���ο� ī�װ� ����� �����ϱ����� StringBuffer ���ڿ� ����
       
     }
   }
   
   mav.addObject("name_title_list", name_title_list);
   mav.addObject("total_count", contentsProc.total_count());
   
   return mav;
 } 
  
  
}






