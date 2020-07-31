package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

//service
public class DeleteArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public void delete(DeleteRequest delReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
// 2개의 메서드 한번에 실행 하기 위해 일단 false
			conn.setAutoCommit(false);
			
// 게시글 번호에 해당하는 article객체 구함			
			Article article = articleDao.selectById(conn,
					delReq.getArticleNumber());
			if(article == null) {
				throw new ArticleNotFoundException();
			}
// 자격여부 검사			
			if (!conDelete(delReq.getUserId(), article)) {
				throw new PermissionDeniedException();
			}
// dao 삭제 메서드	<여기가 핵심!!>
			articleDao.delete(
					conn,
					delReq.getArticleNumber());
			contentDao.delete(
					conn,
					delReq.getArticleNumber());
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new  RuntimeException(e);
		} catch (PermissionDeniedException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private boolean conDelete(String deleteuserId, Article article) {
		return article.getWriter().getId().equals(deleteuserId);
	}
	

}
















