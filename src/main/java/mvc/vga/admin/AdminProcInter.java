package mvc.vga.admin;

import javax.servlet.http.HttpSession;

public interface AdminProcInter {
  /**
   * ������ ���
   * @return
   */
  public String list();
  
  /**
   * ���������� �˻�
   * @param session
   * @return
   */
  public boolean isAdmin(HttpSession session);
  
  /**
   * �α��� ó��
   * @param id_admin
   * @param passwd_admin
   * @return
   */
  public boolean login(String id_admin, String passwd_admin);
    
}
   