package com.qyt.cookie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class LastAccessServlet
 */
@WebServlet("/LastAccessServlet")
public class LastAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LastAccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//�趨������������ı���
				response.setContentType("text/html;charset=utf-8");
				//�����ϴη���ʱ��
				String lastAccessTime = null;
				//��ȡ����cookie
				Cookie[] cookies = request.getCookies();
				//����cookies���飬�����lastAccessTime���ͻ�ȡ��ֵ
				for (int i = 0; cookies!=null&&i < cookies.length; i++) {
					if ("lastAccess".equals(cookies[i].getName())) {
						//�����cookie������lastAccess���ͻ�ȡ����ֵ
						lastAccessTime = cookies[i].getValue();
						//��ȡ��cookie��break;
						break;
					}
				}
				
				//�ж��Ƿ��ȡ��cookieֵ
				if (lastAccessTime == null) {
					response.getWriter().print("�����״η��ʱ�վ��");
				}
				else {
					response.getWriter().print("���ϴη��ʵ�ʱ���ǣ�"+lastAccessTime);
				}
				
				//����cookie,��ֵ���͸��ͻ���
				String currentTime = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss").format(new Date());
				Cookie cookie = new Cookie("lastAccess", currentTime);
				cookie.setMaxAge(5);
				//����cookie
				response.addCookie(cookie);
				//����cookie����Чʱ��
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
