package mvc.vga.attachfile;

import java.util.List;

public interface AttachfileProcInter {
  /**
   * ���� ���  
   * @param attachfileVO
   * @return
   */
  public int create(AttachfileVO attachfileVO);

  /**
   * ��ü �̹��� ���
   * @return
   */
  public List<AttachfileVO> list(); 
  
  /**
   * ��ȸ
   * @param attachfileno
   * @return
   */
  public AttachfileVO read(int attachfileno);
  

  /**
   * ����
   * @param attachfileno
   * @return
   */
  public int delete(int attachfileno);
  
  /**
   * contentsno�� ���� ���� ���
   * @param contentsno
   * @return
   */
  public List<AttachfileVO> list_by_contentsno(int contentsno);
  
  
}
 