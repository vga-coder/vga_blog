package mvc.vga.mem;

import java.util.HashMap;
import java.util.List;

public interface MemDAOInter {

	/**
	 * ���̵� �ߺ� üũ
	 * 
	 * @param id
	 * @return 0: �ߺ� �ƴ�, 1: �ߺ�
	 */
	public int checkID(String mem_id);

	/**
	 * ȸ�� ����
	 * 
	 * @param memberVO
	 * @return
	 */
	public int create(MemVO memVO);

	/**
	 * mem_levelno ASC, memno ASC
	 * 
	 * @return
	 */
	public List<MemVO> list();

	/**
	 * ����
	 * 
	 * @param memno
	 * @return
	 */
	public int delete(int memno);

	/**
	 * ȸ�� ��ȸ
	 * 
	 * @param
	 * @return
	 */
	public MemVO readById(String mem_id);
	
	/**
	 * ȸ�� ��ȸ
	 * 
	 * @param
	 * @return
	 */
	public MemVO read(int memno);

	/**
	 * �α���
	 * 
	 * @param map
	 * @return 0: ��ġ���� ����, 1: ��ġ��
	 */
	public int login(HashMap<Object, Object> map);

	/**
	 * �н����� �˻�
	 * 
	 * @param map
	 * @return 0: ��ġ���� ����, 1: ��ġ��
	 */
	public int passwd_check(HashMap<Object, Object> map);

	/**
	 * �н����� ����
	 * 
	 * @param map
	 * @return ����� �н����� ����
	 */
	public int passwd_update(HashMap<Object, Object> map);
	
	/**
	 * ����ó ����
	 * 
	 * @param map
	 * @return
	 */
	public int contact_update(MemVO memVO);
	
	/**
	 * ȸ�� ���� ����
	 * 
	 * @param map
	 * @return
	 */
	public int mem_img_update(MemVO memVO);
	
	
}
