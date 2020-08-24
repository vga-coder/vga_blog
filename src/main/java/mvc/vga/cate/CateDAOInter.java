package mvc.vga.cate;

import java.util.List;

import mvc.vga.categrp.CategrpVO;

public interface CateDAOInter {

  /**
   * 등록
   * @param cateVO
   * @return
   */
  public int create(CateVO cateVO);
  
  /**
   * 출력 순서별 목록
   * @return
   */
  public List<CateVO> list_seqno_asc();
  
  /**
   * 조회, 수정폼
   * @param categrpno 카테고리 그룹 번호, PK
   * @return
   */
  public CateVO read(int cateno);
  
  /**
   * 수정 
   * @param cateVO
   * @return
   */
  public int update(CateVO cateVO);
  
  /**
   * 삭제 처리
   * @param cateno
   * @return
   */
  public int delete(int cateno);
 
  /**
   * 우선순위 상향, up 10 ▷ 1
   * @param cateno
   * @return
   */
  public int update_seqno_up(int cateno);
  
  /**
   * 우선순위 하향, up 10 ▷ 1
   * @param cateno
   * @return
   */
  public int update_seqno_down(int cateno);
  
  /**
   * 출력 모드의 변경
   * @param cateVO
   * @return
   */
  public int update_visible(CateVO cateVO);
  

  /**
   * <xmp>
   *  통합 VO 기반 join
   *  <select id="list_join" resultType="Categrp_Cate_join"> 
   * </xmp>
   * @return
   */
  public List<Categrp_Cate_join> list_join();
  
  /**
   * <xmp>
   *  categrpno 별 cate 목록: categrp + cate inner join,  1 : 다, 통합 VO
   *  <select id="list_join_by_categrpno" resultType="Categrp_Cate_join" parameterType="int">
   * </xmp>
   * @param categrpno
   * @return
   */
  public List<Categrp_Cate_join> list_join_by_categrpno(int categrpno);
  
  /**
   * <xmp>
   * 전체 카테고리 목록
   * <resultMap type="Categrp_Cate_VO" id="Categrp_Cate_VO_Map">
   *                                      ↑                                    ↑
   *                       ┌────┘                                    └──┐                 
   *                       │                                                            │          
   *                       │      <select id="list_all" resultMap="Categrp_Cate_VO_Map">
   * public List<Categrp_Cate_VO> list_all();
   * </xmp>
   * @return
   */
  public List<Categrp_Cate_VO> list_all();
  
  /**
   * <xmp>
   * categrpno 변수의 값에 해당하는 cate 목록만 추출
   * <resultMap type="Categrp_Cate_VO_list" id="Categrp_Cate_VO_list_Map">
   *                                           ↑                                    ↑
   *                ┌────────┘                                    └──┐                 
   *                │                                                                        │     
   *                │<select id="list_by_categrpno" resultMap="Categrp_Cate_VO_list_Map"  
   *                │                                            parameterType="int">
   * public Categrp_Cate_VO_list list_by_categrpno();                                            
   * </xmp>
   * @return
   */
  public Categrp_Cate_VO_list list_by_categrpno(int categrpno);
  
  /**
   * 글 수 증가
   * <update id="increaseCnt" parameterType="int">
   * @return
   */
  public int increaseCnt(int cateno);    

  /**
   * 글 수 감소
   * <update id="decreaseCnt" parameterType="int">
   * @return
   */
  public int decreaseCnt(int cateno);    
  
}








