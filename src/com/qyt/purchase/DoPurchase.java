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
		
		//��ȡ�û��������Ʒ
				String id = request.getParameter("id");
				//���ͼ�鲻����
				if (id == null) {
					//�ض���ListBookServletҳ��
					String url = "/chapter5/ListBookServlet";
					response.sendRedirect(url);
					return;
				}
				Book book = BookDb.getBook(id);
				
				//�������ȡ�û���session����
				HttpSession session = request.getSession();
				//��session�л�ȡ���ﳵ
				List<Book> cart = (List<Book>) session.getAttribute("cart");
				if (cart == null) {
					//�״ι��򣬴���һ�����ﳵ��list����ģ�⹺�ﳵ��
					cart =  new ArrayList<Book>();
					//�����ﳵ����session����
					session.setAttribute("cart", cart);
				}
//				����Ʒ���빺�ﳵ
				cart.add(book);
				//����cookie��ŵı�ʶ��
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				cookie.setMaxAge(60);
				cookie.setPath("/chapter5");
				response.addCookie(cookie);
//				�ض��򵽹��ﳵҳ��
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
