package com.qyt.purchase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qyt.book.Book;
import com.qyt.databbasse.BookDb;

/**
 * Servlet implementation class DoPurchase
 */
@WebServlet("/DoPurchase")
public class DoPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoPurchase() {
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
		
		//获取用户购买的商品
				String id = request.getParameter("id");
				//如果图书不存在
				if (id == null) {
					//重定向到ListBookServlet页面
					String url = "/chapter5/ListBookServlet";
					response.sendRedirect(url);
					return;
				}
				Book book = BookDb.getBook(id);
				
				//创建或获取用户的session对象
				HttpSession session = request.getSession();
				//从session中获取购物车
				List<Book> cart = (List<Book>) session.getAttribute("cart");
				if (cart == null) {
					//首次购买，创建一个购物车（list集合模拟购物车）
					cart =  new ArrayList<Book>();
					//将购物车放入session对象
					session.setAttribute("cart", cart);
				}
//				将商品放入购物车
				cart.add(book);
				//创建cookie存放的标识号
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				cookie.setMaxAge(60);
				cookie.setPath("/chapter5");
				response.addCookie(cookie);
//				重定向到购物车页面
				response.sendRedirect("/chapter5/CartServlet");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
