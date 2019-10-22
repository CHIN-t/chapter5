package com.qyt.session;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qyt.book.Book;


/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//设置服务器响应编码
				response.setContentType("text/html;charset=utf-8");
				//设置购物车变量
				List<Book> cart = null;
				//标记用户是否购买过商品
				boolean purFlag = true;
				//获取用户的session,不创建新的
				HttpSession session = request.getSession(false);
				//如果session为空，则将购买标记置空
				if (session == null) {
					purFlag = false;
				}
				else {
					//获取用户的购物车
					cart = (List<Book>) session.getAttribute("cart");
//					如果购物车为空，购买标记置空
					if (cart == null) {
						purFlag = false;
					}
				}
				
				//如果购买标记为false，表示没有添加商品，重定向到list页面
				if (purFlag == false) {
					response.getWriter().print("对不起，你没有购买商品");
					//response.sendRedirect("/chapter5/ListBookServlet");
				}else {
					//显示购物车
					response.getWriter().print("你购买的图书有：<br />");
					for (Book book : cart) {
						response.getWriter().print(book.getName()+"<br />");
					}
				}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
