package mvc.vga.contents;

import java.util.List;

import mvc.vga.cate.CateVO;
import mvc.vga.mem.MemVO;

public class Cate_Contents_Member_VO {
  /** ȸ�� ���� */
  private MemVO memVO;
  
  /** ī�װ� + ������ ��� */
  private List<Cate_Contents_VO> cate_contents_memberno_list;

  public MemVO getMemVO() {
    return memVO;
  }

  public void setMemVO(MemVO memVO) {
    this.memVO = memVO;
  }

  public List<Cate_Contents_VO> getCate_contents_memberno_list() {
    return cate_contents_memberno_list;
  }

  public void setCate_contents_memberno_list(List<Cate_Contents_VO> cate_contents_memberno_list) {
    this.cate_contents_memberno_list = cate_contents_memberno_list;
  }  

  
}








