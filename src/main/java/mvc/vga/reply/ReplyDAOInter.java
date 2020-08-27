package mvc.vga.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReplyDAOInter {
  /**
   * 댓글 등록
   * @param replyVO
   * @return
   */
  public int create(ReplyVO replyVO);
  
  public List<ReplyVO> list();
  
  public List<ReplyVO> list_by_contentsno(int contentsno);
  
  public List<ReplyMemberVO> list_by_contentsno_join(int contentsno);
  
  public int checkPasswd(Map<String, Object> map);

  public int delete(int replyno);
  
  /**
   * 더보기 버튼
   * @param map
   * @return
   */
  public List<ReplyMemberVO> list_by_contentsno_join_add_view(HashMap<String, Object> map);

  /**
   * 조회
   * @param replyno
   * @return
   */
  public ReplyMemberVO read(int replyno);
  
}






