package mvc.vga.categrp;

import java.util.List;

// MyBATIS�� <mapper namespace="dev.mvc.categrp.CategrpDAOInter">�� ���� 
public interface CategrpDAOInter {
  // ���ۿ� ���� �޼ҵ���� MyBATIS�� XML ���Ͼ��� id�� �����ؾ��մϴ�.
  
  /**
   * ���
   * <insert id="create" parameterType="CategrpVO">
   * @param categrpVO
   * @return ��ϵ� ���ڵ� ����
   */
  public int create(CategrpVO categrpVO);
  
  /**
   * ���
   * <xmp>
   * <select id="list_categrpno_asc" resultType="CategrpVO">
   * </xmp> 
   * @return ���ڵ� ���
   */
  public List<CategrpVO> list_categrpno_asc();  
  
  /**
   * ��� ������ ���
   * @return
   */
  public List<CategrpVO> list_seqno_asc();
  
  
  /**
   * ��ȸ, ������
   * <xmp>
   *   <select id="read" resultType="CategrpVO" parameterType="int">
   * </xmp>  
   * @param categrpno ī�װ� �׷� ��ȣ, PK
   * @return
   */
  public CategrpVO read(int categrpno);
  
  /**
   * ���� ó��
   * <xmp>
   *   <update id="update" parameterType="CategrpVO"> 
   * </xmp>
   * @param categrpVO
   * @return ó���� ���ڵ� ����
   */
  public int update(CategrpVO categrpVO);
  
  /**
   * ���� ó��
   * <xmp>
   *   <delete id="delete" parameterType="int">
   * </xmp> 
   * @param categrpno
   * @return ó���� ���ڵ� ����
   */
  public int delete(int categrpno);
  
  /**
   * ��� ���� ����
   * <xmp>
   * <update id="update_seqno_up" parameterType="int">
   * </xmp>
   * @param categrpno
   * @return ó���� ���ڵ� ����
   */
  public int update_seqno_up(int categrpno);
 
  /**
   * ��� ���� ����
   * <xmp>
   * <update id="update_seqno_down" parameterType="int">
   * </xmp>
   * @param categrpno
   * @return ó���� ���ڵ� ����
   */
  public int update_seqno_down(int categrpno); 

  /**
   * visible ����
   * @param categrpVO
   * @return
   */
  public int update_visible(CategrpVO categrpVO);
  
}






