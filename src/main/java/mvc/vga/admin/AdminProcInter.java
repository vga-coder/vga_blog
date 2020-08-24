package mvc.vga.admin;

import javax.servlet.http.HttpSession;

public interface AdminProcInter {
  /**
   * 관리자 목록
   * @return
   */
  public String list();
  
  /**
   * 관리자인지 검사
   * @param session
   * @return
   */
  public boolean isAdmin(HttpSession session);
  
  /**
   * 로그인 처리
   * @param id_admin
   * @param passwd_admin
   * @return
   */
  public boolean login(String id_admin, String passwd_admin);
    
}
   