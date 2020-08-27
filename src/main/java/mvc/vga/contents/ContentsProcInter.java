package mvc.vga.contents;

import java.util.HashMap;
import java.util.List;

public interface ContentsProcInter {
  /**
   * 등록 처리
   * 
   * @param contentsVO
   * @return
   */
  public int create(ContentsVO contentsVO);

  /**
   * 전체 목록
   * 
   * @return
   */
  public List<ContentsVO> list_all();

  /**
   * 조회
   * 
   * @param contentsno
   * @return
   */
  public ContentsVO read(int contentsno);

  /**
   * 수정 폼
   * 
   * @param contentsVO
   * @return
   */
  public ContentsVO update(int contentsno);

  /**
   * 수정 처리
   * 
   * @param contentsVO
   * @return
   */
  public int update(ContentsVO contentsVO);

  /**
   * 패스워드 검사
   * 
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap hashMap);

  /**
   * 삭제
   * 
   * @param contentsno
   * @return
   */
  public int delete(int contentsno);

  /**
   * <xmp> cateno별 목록
   * <select id="list" resultType="ContentsVO" parameterType="int"> </xmp>
   * 
   * @return
   */
  public List<ContentsVO> list(int cateno);

  /**
   * <xmp> 전체 게시글 갯수 <select id="total_count" resultType="int"> </xmp>
   * 
   * @return
   */
  public int total_count();

  /**
   * 회원별 글 목록 <xmp>
   * <select id="cate_contents_memberno_list" parameterType="int" resultMap=
   * "Cate_Contents_VO_Map"> </xmp>
   */
  public List<Cate_Contents_VO> cate_contents_memberno_list(int memno);

  /**
   * 지도의 등록, 수정, 삭제
   * @param hashMap
   * @return
   */
  public int map(HashMap hashMap);

  /**
   * <xmp>
   *   Youtube 등록, 수정, 삭제
       <update id="youtube" parameterType="HashMap">
   * </xmp> 
   * @param hashMap
   * @return
   */
  public int youtube(HashMap<Object, Object> hashMap);

  /**
   * <xmp>
   *   MP3 등록, 수정, 삭제
       <update id="mp3" parameterType="HashMap">
   * </xmp> 
   * @param hashMap
   * @return
   */
  public int mp3(HashMap<Object, Object> hashMap);
 
  /**
   * <xmp>
   *   MP4 등록, 수정, 삭제
       <update id="mp4" parameterType="HashMap">
   * </xmp> 
   * @param hashMap
   * @return
   */
  public int mp4(HashMap<Object, Object> hashMap);
  
  /**
   * 카테고리별 검색 목록
   * @param hashMap
   * @return
   */
  public List<ContentsVO> list_by_cateno_search(HashMap<String, Object> hashMap);

  /**
   * 카테고리별 검색 레코드 갯수
   * @param hashMap
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * <xmp>
   * 검색 + 페이징 목록
   * <select id="list_by_cateno_search_paging" resultType="ContentsVO" parameterType="HashMap">
   * </xmp>
   * @param map
   * @return
   */
  public List<ContentsVO> list_by_cateno_search_paging(HashMap<String, Object> map);

  /**
   * 페이지 목록 문자열 생성, Box 형태
   * @param listFile 목록 파일명 
   * @param cateno 카테고리번호
   * @param search_count 검색 갯수
   * @param nowPage 현재 페이지, nowPage는 1부터 시작
   * @param word 검색어
   * @return
   */
  public String pagingBox(String listFile, int cateno, int search_count, int nowPage, String word);

  /**
   * 답변 순서 증가
   * <update id="increaseAnsnum" parameterType="HashMap"> 
   * @param map
   * @return
   */
  public int increaseAnsnum(HashMap<String, Object> map);
   
  /**
   * <xmp>
   * 답변
   * <insert id="reply" parameterType="ContentsVO">
   * </xmp>
   * @param contentsVO
   * @return
   */
  public int reply(ContentsVO contentsVO);

  /**
   * 글 수 증가
   * @param 
   * @return
   */ 
  public int increaseReplycnt(int contentsno);
 
  /**
   * 글 수 감소
   * @param 
   * @return
   */   
  public int decreaseReplycnt(int contentsno);
  
}





