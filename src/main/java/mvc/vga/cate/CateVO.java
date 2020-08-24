package mvc.vga.cate;

/*
CREATE TABLE cate(
    cateno                            NUMBER(10)     NOT NULL    PRIMARY KEY,
    categrpno                       NUMBER(10)     NOT NULL,
    name                              VARCHAR2(100)    NOT NULL,
    seqno                             NUMBER(10)     DEFAULT 1     NOT NULL,
    visible                             CHAR(1)    DEFAULT 'Y'     NOT NULL,
    rdate                              DATE     NOT NULL,
    cnt                                 NUMBER(10)     DEFAULT 0     NOT NULL,
  FOREIGN KEY (categrpno) REFERENCES categrp (categrpno)
); 
 */
public class CateVO {
  /** 카테고리 번호 */
  private int cateno;  
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
  /** 등록된 글 수 */
  private int cnt;
  
  public CateVO() {
  
  }
  
  public CateVO(int cateno, int categrpno, String name, int seqno, String visible, String rdate, int cnt) {
    this.cateno = cateno;
    this.categrpno = categrpno;
    this.name = name;
    this.seqno = seqno;
    this.visible = visible;
    this.rdate = rdate;
    this.cnt = cnt;
  }

  public int getCateno() {
    return cateno;
  }
  public void setCateno(int cateno) {
    this.cateno = cateno;
  }
  public int getCategrpno() {
    return categrpno;
  }
  public void setCategrpno(int categrpno) {
    this.categrpno = categrpno;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
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
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  
  
}

