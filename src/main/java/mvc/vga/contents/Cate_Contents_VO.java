package mvc.vga.contents;

public class Cate_Contents_VO {
  // Cate table
  /** ī�װ� ��ȣ */
  private int cateno;  
  /**  ī�װ� �̸� */
  private String name;
  /** ��� ��¥ */
  private String cate_rdate; // cate, contents ���̺��� rdate �ߺ� �߻� �ذ�
  
  // Contents table
  /** ������ ��ȣ */
  private int contentsno;
  /** ���� */
  private String title;
  /** ���ͳ� �ּ�*/
  private String web;  
  /** IP */
  private String ip;
  /** ��� ��¥ */
  private String rdate;
  
  public int getCateno() {
    return cateno;
  }
  public void setCateno(int cateno) {
    this.cateno = cateno;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getContentsno() {
    return contentsno;
  }
  public void setContentsno(int contentsno) {
    this.contentsno = contentsno;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getWeb() {
    return web;
  }
  public void setWeb(String web) {
    this.web = web;
  }
  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getCate_rdate() {
    return cate_rdate;
  }
  public void setCate_rdate(String cate_rdate) {
    this.cate_rdate = cate_rdate;
  }
  
  
}
