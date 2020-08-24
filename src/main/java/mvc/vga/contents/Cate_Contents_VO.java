package mvc.vga.contents;

public class Cate_Contents_VO {
  // Cate table
  /** 카테고리 번호 */
  private int cateno;  
  /**  카테고리 이름 */
  private String name;
  /** 등록 날짜 */
  private String cate_rdate; // cate, contents 테이블의 rdate 중복 발생 해결
  
  // Contents table
  /** 컨텐츠 번호 */
  private int contentsno;
  /** 제목 */
  private String title;
  /** 인터넷 주소*/
  private String web;  
  /** IP */
  private String ip;
  /** 등록 날짜 */
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
