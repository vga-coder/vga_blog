package mvc.vga.categrp;
 
/*
    categrpno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
    name                              VARCHAR2(50)     NOT NULL,
    seqno                             NUMBER(7)    DEFAULT 0     NOT NULL,
    visible                           CHAR(1)    DEFAULT 'Y'     NOT NULL,
    rdate                                 DATE     NOT NULL 
 */
public class CategrpVO {
  /** 카테고리 그룹 번호 */
  private int categrpno;
  /**  카테고리 이름 */
  private String name;
  /** 출력 순서 */
  private int seqno;
  /** 출력 모드 */
  private String visible;
  /** 등록일 */
  private String rdate;
  
  public CategrpVO() {

  }
  
  public CategrpVO(int categrpno, String name, int seqno, String visible, String rdate) {
    this.categrpno = categrpno;
    this.name = name;
    this.seqno = seqno;
    this.visible = visible;
    this.rdate = rdate;
  }

  public String getName() {
    return name;
  }
 
  public void setName(String name) {
    // System.out.println("--> CategrpVO setName(\""+name+"\") 호출됨.");
    this.name = name;
  }
 
  public int getCategrpno() {
    return categrpno;
  }
 
  public void setCategrpno(int categrpno) {
    this.categrpno = categrpno;
  }
 
  public int getSeqno() {
    return seqno;
  }
 
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
 
  public String getVisible() {
    return visible;
  }
 
  public void setVisible(String visible) {
    this.visible = visible;
  }
 
  public String getRdate() {
    return rdate;
  }
 
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
 
  
} 
 
  