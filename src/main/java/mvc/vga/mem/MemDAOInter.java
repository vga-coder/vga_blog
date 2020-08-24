package mvc.vga.mem;

import java.util.HashMap;
import java.util.List;

public interface MemDAOInter {

	/**
	 * 아이디 중복 체크
	 * 
	 * @param id
	 * @return 0: 중복 아님, 1: 중복
	 */
	public int checkID(String mem_id);

	/**
	 * 회원 가입
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
	 * 삭제
	 * 
	 * @param memno
	 * @return
	 */
	public int delete(int memno);

	/**
	 * 회원 조회
	 * 
	 * @param
	 * @return
	 */
	public MemVO readById(String mem_id);
	
	/**
	 * 회원 조회
	 * 
	 * @param
	 * @return
	 */
	public MemVO read(int memno);

	/**
	 * 로그인
	 * 
	 * @param map
	 * @return 0: 일치하지 않음, 1: 일치함
	 */
	public int login(HashMap<Object, Object> map);

	/**
	 * 패스워드 검사
	 * 
	 * @param map
	 * @return 0: 일치하지 않음, 1: 일치함
	 */
	public int passwd_check(HashMap<Object, Object> map);

	/**
	 * 패스워드 변경
	 * 
	 * @param map
	 * @return 변경된 패스워드 갯수
	 */
	public int passwd_update(HashMap<Object, Object> map);
	
	/**
	 * 연락처 변경
	 * 
	 * @param map
	 * @return
	 */
	public int contact_update(MemVO memVO);
	
	/**
	 * 회원 사진 수정
	 * 
	 * @param map
	 * @return
	 */
	public int mem_img_update(MemVO memVO);
	
	
}
