package mvc.vga.cate;

/*
    SELECT r.categrpno as r_categrpno, r.name as r_name,
               c.cateno, c.categrpno, c.name, c.seqno, c.visible, c.rdate, c.cnt    FROM categrp r, cate c
    WHERE r.categrpno = c.categrpno
    ORDER BY r.categrpno ASC, c.seqno ASC
 */
public class Categrp_Cate_join {
  // -------------------------------------------------------------------
  // Categrp table
  // -------------------------------------------------------------------
  /** �θ� ���̺� ī�װ� �׷� ��ȣ */
  private int r_categrpno;
  /** �θ� ���̺� ī�װ� �׷� �̸� */
  private String r_name;
  /** ī�װ� ��ȣ */
  
  // -------------------------------------------------------------------
  // Cate table
  // -------------------------------------------------------------------  
  private int cateno;  
  /** ī�װ� �׷� ��ȣ */
  private int categrpno;
  /**  ī�װ� �̸� */
  private String name;
  /** ��� ���� */
  private int seqno;
  /** ��� ��� */
  private String visible;
  /** ����� */
  private String rdate;
  /** ��ϵ� �� �� */
  private int cnt;
  
  public int getR_categrpno() {
    return r_categrpno;
  }
  public void setR_categrpno(int r_categrpno) {
    this.r_categrpno = r_categrpno;
  }
  public String getR_name() {
    return r_name;
  }
  public void setR_name(String r_name) {
    this.r_name = r_name;
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








