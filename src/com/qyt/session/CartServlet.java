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
		
		//���÷�������Ӧ����
				response.setContentType("text/html;charset=utf-8");
				//���ù��ﳵ����
				List<Book> cart = null;
				//����û��Ƿ������Ʒ
				boolean purFlag = true;
				//��ȡ�û���session,�������µ�
				HttpSession session = request.getSession(false);
				//���sessionΪ�գ��򽫹������ÿ�
				if (session == null) {
					purFlag = false;
				}
				else {
					//��ȡ�û��Ĺ��ﳵ
					cart = (List<Book>) session.getAttribute("cart");
//					������ﳵΪ�գ��������ÿ�
					if (cart == null) {
						purFlag = false;
					}
				}
				
				//���������Ϊfalse����ʾû�������Ʒ���ض���listҳ��
				if (purFlag == false) {
					response.getWriter().print("�Բ�����û�й�����Ʒ");
					//response.sendRedirect("/chapter5/ListBookServlet");
				}else {
					//��ʾ���ﳵ
					response.getWriter().print("�㹺���ͼ���У�<br />");
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
