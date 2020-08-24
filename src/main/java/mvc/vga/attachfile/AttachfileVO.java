package mvc.vga.attachfile;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class AttachfileVO {
  /*
    attachfileno                  NUMBER(10)     NOT NULL    PRIMARY KEY,
    contentsno                   NUMBER(10)    NULL ,
    fname                           VARCHAR2(100)    NOT NULL,
    fupname                      VARCHAR2(100)     NOT NULL,
    thumb                         VARCHAR2(100)    NULL ,
    fsize                             NUMBER(10)     DEFAULT 0     NOT NULL,
   */
  /** ���� ��ȣ */
  private int attachfileno;
  /** �� ��ȣ(FK) */
  private int contentsno;
  /** ���� ���ϸ� */
  private String fname;
  /** ���ε�� ���ϸ� */
  private String fupname;
  /** Thumb �̹��� */
  private String thumb;
  /** ���� ũ�� */
  private long fsize;
  /** ����� */
  private String rdate;
  
  /** Form�� ������ MultipartFile�� ��ȯ�Ͽ� List�� ����  */
  private List<MultipartFile> fnamesMF;
  
  // private MultipartFile fnamesMF;  // �ϳ��� ���� ó��
  /** ���� ���� ��� */
  private String flabel;   
  
  public int getAttachfileno() {
    return attachfileno;
  }
  public void setAttachfileno(int attachfileno) {
    this.attachfileno = attachfileno;
  }
  public int getContentsno() {
    return contentsno;
  }
  public void setContentsno(int contentsno) {
    this.contentsno = contentsno;
  }
  public String getFname() {
    return fname;
  }
  public void setFname(String fname) {
    this.fname = fname;
  }
  public String getFupname() {
    return fupname;
  }
  public void setFupname(String fupname) {
    this.fupname = fupname;
  }
  public String getThumb() {
    return thumb;
  }
  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
  public long getFsize() {
    return fsize;
  }
  public void setFsize(long fsize) {
    this.fsize = fsize;
  }
  public List<MultipartFile> getFnamesMF() {
    return fnamesMF;
  }
  public void setFnamesMF(List<MultipartFile> fnamesMF) {
    this.fnamesMF = fnamesMF;
  }
  public String getFlabel() {
    return flabel;
  }
  public void setFlabel(String flabel) {
    this.flabel = flabel;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }  
    
}

