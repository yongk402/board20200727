package article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.DeleteArticleService;
import article.service.DeleteRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import auth.service.User;
import mvc.controller.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/deleteForm.jsp";
	
	private ReadArticleService readService = new ReadArticleService();
	private DeleteArticleService deleteService = new DeleteArticleService();
		
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
	}
 
}

//폼 요청 처리
	private String processForm(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
		
//폼에 보여줄 게시글 구한다.			
			ArticleData articleData = readService.getArticle(no, false);
//현재 로그인한 사용자 정보 구한다.			
			User authUser = (User) req.getSession().getAttribute("authUser");
//현재 로그인한 사용자가 작성자 아니면 에러403 전송			
			if (!canDelete(authUser, articleData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
//폼에 필요 데이터 사용객체 생성, request의 delReq속성에 저장			
			DeleteRequest delReq = new DeleteRequest(
					authUser.getId(), 
					no
					);
			req.setAttribute("delReq", delReq);
//폼을 위한 view 리턴			
			return FORM_VIEW;
			
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private boolean canDelete(User authUser, ArticleData articleData) {
		String writerId = articleData.getArticle().getWriter().getId();
		return authUser.getId().equals(writerId);
	}

	
//폼 전송을 처리	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
//삭제하려는 사용자 정보 구한다.		
		User authUser = (User) req.getSession().getAttribute("authUser");
		
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
//요청파라미터와 현재 사용자 정보 이용해서  DeleteRequest 객체 생성		
		DeleteRequest delReq = new DeleteRequest(
				authUser.getId(), 
				no 
				);
//DeleteRequest 객체를 request의 delReq 속성에 저장	
		req.setAttribute("delReq", delReq);
//에러정보담을 맵객체 생성//객체 유효 검사-유효안하면 다시 폼 리턴		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
//삭제 기능 실행	 [핵심!!]		
			deleteService.delete(delReq);
			return "/WEB-INF/view/deleteSuccess.jsp";

		} catch (ArticleNotFoundException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
	
}		
	


