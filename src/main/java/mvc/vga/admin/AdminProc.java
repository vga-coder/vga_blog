package mvc.vga.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mvc.vga.admin.AdminProc")
public class AdminProc implements AdminProcInter {
  @Value("${admin1}")  // properties ����
  private String admin1;

  @Value("${admin2}")
  private String admin2;
  
  @Value("${admin3}")
  private String admin3;
  
  /**
   * ������ ���� �˻�
   * @param info
   * @param id
   * @return
   */
  public boolean admin_check(String id_admin, String passwd_admin) {
    boolean sw = false;
    String[] admins = {"admin1/1234", admin2, admin3};
    // "admin1/1234/������1", "admin2/1234/������2", "admin3/1234/������3"
    
    for (String admin:admins) {
      String[] tokens = admin.split("/"); // "admin1/1234/������1"  
      if (tokens[0].equals(id_admin) && tokens[1].equals(passwd_admin)) {
        sw = true;
      }
    }
    
    return sw;
  }
  
  /**
   * ������ �α��� ó��
   */
  @Override
  public boolean login(String id_admin, String passwd_admin){
    System.out.println(admin1);
    System.out.println(admin2);
    System.out.println(admin3);
   
    // ������ ���̵�, �н����� �˻�
    boolean sw = admin_check(id_admin, passwd_admin); 
    
    return sw;
  }
  
  /**
   * ���� �α��ε� �������� üũ
   */
  @Override
  public boolean isAdmin(HttpSession session){
    boolean sw = false;
    
    String id_admin = (String)session.getAttribute("id_admin");
    
    if (id_admin != null){
      sw = true;
    }
    return sw;
  }
  
  /**
   * ������ ���
   */
  @Override
  public String list() {
    String admins = "";
    admins = admin1 + "\n" + admin2 + "\n" + admin3;  
    
    return admins;
  }
  
}





