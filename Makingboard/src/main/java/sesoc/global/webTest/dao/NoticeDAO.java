package sesoc.global.webTest.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import sesoc.global.webTest.vo.Notice;

public interface NoticeDAO {
	public List<Notice> select(Map<String, String> search, RowBounds rb);
	public int insert(Notice notice);
	public int delete(int noticenum);
	public int update(Notice notice);
	public Notice selectOne(int noticenum);
	public int getNoticeCount(Map<String, String> map); // 전체 글 갯수
	public int incrementCount(int noticenum);
}//class
