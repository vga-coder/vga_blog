package mvc.vga.attachfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import mvc.vga.contents.ContentsDAOInter;
import mvc.vga.tool.Tool;

@Component("mvc.vga.attachfile.AttachfileProc")
public class AttachfileProc implements AttachfileProcInter {
  @Autowired  // DI, Spring framework이 자동 구현한 DAO가 자동 할당됨.
  private AttachfileDAOInter attachfileDAO;
  
  public AttachfileProc(){

  }

  @Override
  public int create(AttachfileVO attachfileVO) {
    int cnt = 0;
    cnt = this.attachfileDAO.create(attachfileVO);
    
    return cnt;
  }

  @Override
  public List<AttachfileVO> list() {
    List<AttachfileVO> list = null;
    list= this.attachfileDAO.list();
    return list;
  }

  @Override
  public int delete(int attachfileno) {
    int cnt = 0;
    cnt = this.attachfileDAO.delete(attachfileno);
    return cnt;
    
  }

  @Override
  public AttachfileVO read(int attachfileno) {
    AttachfileVO attachfileVO =  null;
    attachfileVO = this.attachfileDAO.read(attachfileno);
    
    return attachfileVO;
  }

  /**
   * 첨부 파일 목록, 파일 용량 단위 출력
   */
  @Override
  public List<AttachfileVO> list_by_contentsno(int contentsno) {
    List<AttachfileVO> list = attachfileDAO.list_by_contentsno(contentsno);
    for (AttachfileVO attachfileVO:list) {
      long fsize = attachfileVO.getFsize();
      String flabel = Tool.unit(fsize);  // 파일 단위 적용
      attachfileVO.setFlabel(flabel);
    }
    return list;
  }
  
}





