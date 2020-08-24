package mvc.vga.categrp;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
 

@Component("mvc.vga.categrp.CategrpProc")
public class CategrpProc implements CategrpProcInter {
  @Autowired  // DI, Spring framework이 자동 구현한 DAO가 자동 할당됨.
  private CategrpDAOInter categrpDAO;
  // private CategrpDAOInter categrpDAO = new CategrpDAO();
  
  @Override
  public int create(CategrpVO categrpVO) {
    int cnt = 0;
    cnt = this.categrpDAO.create(categrpVO);
    return cnt;
  }
  
  @Override
  public List<CategrpVO> list_categrpno_asc() {
    List<CategrpVO> list = null;
    list = this.categrpDAO.list_categrpno_asc();
    return list;
  }

  @Override
  public List<CategrpVO> list_seqno_asc() {
    List<CategrpVO> list = null;
    list = this.categrpDAO.list_seqno_asc();
    return list;
  }
  
  @Override
  public CategrpVO read(int categrpno) {
    CategrpVO categrpVO = null;
    categrpVO = this.categrpDAO.read(categrpno);
    
    return categrpVO;
  }

  @Override
  public int update(CategrpVO categrpVO) {
    int cnt = 0;
    cnt = this.categrpDAO.update(categrpVO);
    
    return cnt;
  }

  @Override
  public int delete(int categrpno) {
    int cnt = 0;
    cnt = this.categrpDAO.delete(categrpno);
    
    return cnt;
  }

  @Override
  public int update_seqno_up(int categrpno) {
    int cnt = 0;
    cnt = this.categrpDAO.update_seqno_up(categrpno);
    
    return cnt;
  }

  @Override
  public int update_seqno_down(int categrpno) {
    int cnt = 0;
    cnt = this.categrpDAO.update_seqno_down(categrpno);    
    return cnt;
  }

  @Override
  public int update_visible(CategrpVO categrpVO) {
    int cnt = 0;
    if (categrpVO.getVisible().toUpperCase().equals("Y")) {
      categrpVO.setVisible("N");
    } else {
      categrpVO.setVisible("Y");
    }
    cnt = this.categrpDAO.update_visible(categrpVO);
    return cnt;
  }
  
 
}


