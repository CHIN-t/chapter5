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
		
		//设定服务器输出中文编码
				response.setContentType("text/html;charset=utf-8");
				//设置上次访问时间
				String lastAccessTime = null;
				//获取所有cookie
				Cookie[] cookies = request.getCookies();
				//遍历cookies数组，如果有lastAccessTime，就获取该值
				for (int i = 0; cookies!=null&&i < cookies.length; i++) {
					if ("lastAccess".equals(cookies[i].getName())) {
						//如果有cookie名称是lastAccess，就获取他的值
						lastAccessTime = cookies[i].getValue();
						//获取到cookie，break;
						break;
					}
				}
				
				//判断是否获取到cookie值
				if (lastAccessTime == null) {
					response.getWriter().print("您是首次访问本站！");
				}
				else {
					response.getWriter().print("您上次访问的时间是："+lastAccessTime);
				}
				
				//创建cookie,将值发送给客户端
				String currentTime = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss").format(new Date());
				Cookie cookie = new Cookie("lastAccess", currentTime);
				cookie.setMaxAge(5);
				//发送cookie
				response.addCookie(cookie);
				//设置cookie的有效时间
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
