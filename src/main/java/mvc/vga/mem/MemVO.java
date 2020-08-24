package mvc.vga.mem;

import org.springframework.web.multipart.MultipartFile;

/*	CREATE TABLE mem(
      memno                               NUMBER(10)       NOT NULL       PRIMARY KEY,
      mem_img                             VARCHAR2(1000)       NULL ,
      mem_id                              VARCHAR2(20)       NOT NULL,
      mem_pw                              VARCHAR2(20)       NOT NULL,
      mem_name                            VARCHAR2(20)       NOT NULL,
      mem_yy                              NUMBER(10)       NOT NULL,
      mem_mm                              NUMBER(10)       NOT NULL,
      mem_dd                              NUMBER(10)       NOT NULL,
      mem_telecom                         VARCHAR2(20)       NOT NULL,
      mem_phone                          NUMBER(20)       NOT NULL,
      mem_email                           VARCHAR2(20)       NOT NULL,
      signdate                            DATE       NOT NULL,
      mem_lvno                         NUMBER(10)       DEFAULT 1       NOT NULL,
  FOREIGN KEY (memlvno) REFERENCES memlv (memlvno)
);*/

public class MemVO {
/** ȸ����ȣ */
private int memno;
/** ȸ�� ���� */
private String mem_img;
/** ȸ�� ���̵� */
private String mem_id;
/** ȸ�� �̸�*/
private String mem_name;
/** ȸ�� ��й�ȣ */
private String mem_pw = ""; 
/** ȸ�� �� */
private int mem_yy;
/** ȸ�� �� */
private int mem_mm;
/** ȸ�� �� */
private int mem_dd;
/** ȸ�� ��Ż� */
private String mem_telecom;
/** ȸ�� ��ȭ��ȣ */
private int mem_phone;
/** ȸ�� �̸��� */
private String mem_email;
/** ������ */
private String signdate;


/** �̹��� MultipartFile */
private MultipartFile mem_imgMF;



public MultipartFile getMem_imgMF() {
	return mem_imgMF;
}
public void setMem_imgMF(MultipartFile mem_imgMF) {
	this.mem_imgMF = mem_imgMF;
}


public String getMem_name() {
	return mem_name;
}
public void setMem_name(String mem_name) {
	this.mem_name = mem_name;
}
public int getMemno() {
	return memno;
}
public void setMemno(int memno) {
	this.memno = memno;
}
public String getMem_img() {
	return mem_img;
}
public void setMem_img(String mem_img) {
	this.mem_img = mem_img;
}
public String getMem_id() {
	return mem_id;
}
public void setMem_id(String mem_id) {
	this.mem_id = mem_id;
}
public String getMem_pw() {
	return mem_pw;
}
public void setMem_pw(String mem_pw) {
	this.mem_pw = mem_pw;
}
public int getMem_yy() {
	return mem_yy;
}
public void setMem_yy(int mem_yy) {
	this.mem_yy = mem_yy;
}
public int getMem_mm() {
	return mem_mm;
}
public void setMem_mm(int mem_mm) {
	this.mem_mm = mem_mm;
}
public int getMem_dd() {
	return mem_dd;
}
public void setMem_dd(int mem_dd) {
	this.mem_dd = mem_dd;
}
public String getMem_telecom() {
	return mem_telecom;
}
public void setMem_telecom(String mem_telecom) {
	this.mem_telecom = mem_telecom;
}
public int getMem_phone() {
	return mem_phone;
}
public void setMem_phone(int mem_phone) {
	this.mem_phone = mem_phone;
}
public String getMem_email() {
	return mem_email;
}
public void setMem_email(String mem_email) {
	this.mem_email = mem_email;
}
public String getSigndate() {
	return signdate;
}
public void setSigndate(String signdate) {
	this.signdate = signdate;
}

	
}
