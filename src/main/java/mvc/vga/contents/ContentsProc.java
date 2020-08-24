package mvc.vga.contents;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mvc.vga.categrp.CategrpDAOInter;
import mvc.vga.tool.Tool;

@Component("mvc.vga.contents.ContentsProc")
public class ContentsProc implements ContentsProcInter {
  @Autowired  // DI, Spring framework�� �ڵ� ������ DAO�� �ڵ� �Ҵ��.
  private ContentsDAOInter contentsDAO;
  
  @Override
  public int create(ContentsVO contentsVO) {
    int cnt = 0;
    cnt = this.contentsDAO.create(contentsVO);
    return cnt;
  }

  @Override
  public List<ContentsVO> list_all() {
    List<ContentsVO> list = this.contentsDAO.list_all();
    return list;
  }

  @Override
  public ContentsVO read(int contentsno) {
    ContentsVO contentsVO = this.contentsDAO.read(contentsno);
    
//    System.out.println(contentsVO.toString());
//    
//    if (contentsVO.getWeb() == null) {
//      System.out.println("web null �Դϴ�.");
//    }
//
//    if (contentsVO.getWeb().equals("")) {
//      System.out.println("web ���� �Դϴ�.");
//    }
    
//    String content = contentsVO.getContent();
//    content = Tool.convertChar(content);
//    contentsVO.setContent(content);

    String title = contentsVO.getTitle();
    title = Tool.convertChar(title);
    contentsVO.setTitle(title);
    
    return contentsVO;
  }

  @Override
  public ContentsVO update(int contentsno) {
    ContentsVO contentsVO = this.contentsDAO.read(contentsno);
    
    return contentsVO;
  }
  
  @Override
  public int update(ContentsVO contentsVO) {
    int cnt = 0;
    cnt = this.contentsDAO.update(contentsVO);
    return cnt;
  }

  @Override
  public int passwd_check(HashMap hashMap) {
    int passwd_cnt = 0;
    passwd_cnt = this.contentsDAO.passwd_check(hashMap);
    return passwd_cnt;
  }

  @Override
  public int delete(int contentsno) {
    int cnt = 0;
    cnt = this.contentsDAO.delete(contentsno);
    return cnt;
  }

  @Override
  public List<ContentsVO> list(int cateno) {
    List<ContentsVO> list = this.contentsDAO.list(cateno);
    return list;
  }

  @Override
  public int total_count() {
    int cnt = 0;
    cnt = this.contentsDAO.total_count();
    return cnt;
  }

  @Override
  public List<Cate_Contents_VO> cate_contents_memberno_list(int memberno) {
    List<Cate_Contents_VO> list = this.contentsDAO.cate_contents_memberno_list(memberno);
    return list;
  }

  @Override
  public int map(HashMap hashMap) {
    int cnt = 0;
    cnt = this.contentsDAO.map(hashMap);
    return cnt;
  }

  @Override
  public int youtube(HashMap<Object, Object> hashMap) {
    int cnt = 0;
    cnt = this.contentsDAO.youtube(hashMap);
    return cnt;
  }

  @Override
  public int mp3(HashMap<Object, Object> hashMap) {
    int cnt = 0;
    cnt = this.contentsDAO.mp3(hashMap);
    return cnt;
  }

  @Override
  public int mp4(HashMap<Object, Object> hashMap) {
    int cnt = 0;
    cnt = this.contentsDAO.mp4(hashMap);
    return cnt;
  }
  
  @Override
  public List<ContentsVO> list_by_cateno_search(HashMap<String, Object> hashMap) {
    List<ContentsVO> list = contentsDAO.list_by_cateno_search(hashMap);
    return list;
  }

  @Override
  public int search_count(HashMap<String, Object> hashMap) {
    int count = contentsDAO.search_count(hashMap);
    return count;
  }
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param listFile ��� ���ϸ� 
   * @param categrpno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String pagingBox(String listFile, int cateno, int search_count, int nowPage, String word){ 
    int totalPage = (int)(Math.ceil((double)search_count/Contents.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Contents.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Contents.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Contents.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Contents.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    // ���� 10�� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page)
    // nowGrp: 2 (11 ~ 20 page)
    // nowGrp: 3 (21 ~ 30 page) 
    // ���� 2�׷��� ���: (2 - 1) * 10 = 1�׷��� ������ ������ 10
    // ���� 3�׷��� ���: (3 - 1) * 10 = 2�׷��� ������ ������ 20
    int _nowPage = (nowGrp-1) * Contents.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='"+listFile+"?word="+word+"&nowPage="+_nowPage+"&cateno="+cateno+"'>����</A></span>"); 
    } 
 
    // �߾��� ������ ���
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ // ������ �������� �Ѿ�ٸ� ���� ��� ����
        break; 
      } 
  
      if (nowPage == i){ // �������� ������������ ���ٸ� CSS ����(������ ��)
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� �������� �̵��� �����ϵ��� ��ũ�� ����
        str.append("<span class='span_box_1'><A href='"+listFile+"?word="+word+"&nowPage="+i+"&cateno="+cateno+"'>"+i+"</A></span>");   
      } 
    } 
 
    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� ���������� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� ���������� 21
    _nowPage = (nowGrp * Contents.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='"+listFile+"?word="+word+"&nowPage="+_nowPage+"&cateno="+cateno+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
 
  @Override
  public List<ContentsVO> list_by_cateno_search_paging(HashMap<String, Object> map) {
    /* 
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������: nowPage = 3, (3 - 1) * 10 --> 20
    */
    int beginOfPage = ((Integer)map.get("nowPage") - 1) * Contents.RECORD_PER_PAGE;
   
    // ���� rownum
    // 1 ������ = 0 + 1, 2 ������ = 10 + 1, 3 ������ = 20 + 1 
    int startNum = beginOfPage + 1; 
    //  ���� rownum
    // 1 ������ = 0 + 10, 2 ������ = 0 + 20, 3 ������ = 0 + 30
    int endNum = beginOfPage + Contents.RECORD_PER_PAGE;   
    /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
    map.put("startNum", startNum);
    map.put("endNum", endNum);
   
    List<ContentsVO> list = this.contentsDAO.list_by_cateno_search_paging(map);
    
    // --------------------------------------------------------------
    // ����, ���� ���ڼ� ����
    // --------------------------------------------------------------
    String title = "";
    String content = "";
    for (ContentsVO contentsVO: list) {
      title = contentsVO.getTitle();
      if (title.length() >= 25) {
        title = Tool.textLength(title, 25);
        contentsVO.setTitle(title);
      }
      content = contentsVO.getContent();
      if (content.length() >= 100) {
        content = Tool.textLength(content, 100);
        contentsVO.setContent(content);
      }
    }
    // --------------------------------------------------------------    
    
    return list;
  }
  
  @Override
  public int increaseAnsnum(HashMap<String, Object> map) {
    int count = contentsDAO.increaseAnsnum(map);
    return count;
  }
  
  @Override
  public int reply(ContentsVO contentsVO) {
    int count = contentsDAO.reply(contentsVO);
    return count;
  }
  
  @Override
  public int increaseReplycnt(int contentsno) {
    int count = contentsDAO.increaseReplycnt(contentsno);
    return count;
  }

  @Override
  public int decreaseReplycnt(int contentsno) {
    int count = contentsDAO.decreaseReplycnt(contentsno);
    return count;
  }
  
}




