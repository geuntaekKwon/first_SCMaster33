package sesoc.global.webTest.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sesoc.global.webTest.util.FileService;
import sesoc.global.webTest.vo.Notice;

@Repository
public class NoticeDAORepository{

	@Autowired
	SqlSession sql;
	
	public List<Notice> select(Map<String, String> search, RowBounds rb, int startRecord, int countPerPage) {
		NoticeDAO dao = sql.getMapper(NoticeDAO.class);
		return dao.select(search, rb);
	}//select

	// 완
	public int insert(Notice notice) {
		NoticeDAO dao = sql.getMapper(NoticeDAO.class);
		return dao.insert(notice);
	}//insert

	public int delete(int noticenum) {
		NoticeDAO dao = sql.getMapper(NoticeDAO.class);
		Notice notice = dao.selectOne(noticenum);
		
		FileService.deleteFile("/noticefile/"+notice.getSavedfile());
		return dao.delete(noticenum);
	}//delete

	public int update(Notice notice) {
		NoticeDAO dao = sql.getMapper(NoticeDAO.class);
		return dao.update(notice);
	}

	public Notice selectOne(int noticenum) {
		NoticeDAO dao = sql.getMapper(NoticeDAO.class);
		return dao.selectOne(noticenum);
	}

	public int getNoticeCount(Map<String, String> map) {
		NoticeDAO dao = sql.getMapper(NoticeDAO.class);
		return dao.getNoticeCount(map);
	}

	public int incrementCount(int noticenum) {
		NoticeDAO dao = sql.getMapper(NoticeDAO.class);
		return dao.incrementCount(noticenum);
	}//incrementCount

}
