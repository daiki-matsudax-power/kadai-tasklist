package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Task;
import util.DBUtil;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/*.do")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        //該当のIDのメッセージ一件のみをデータベースから取得
        Task t = em.find(Task.class,Integer.parseInt(request.getParameter("id")));

        em.close();

        //メッセージ情報とセッションIDをリクエストスコープに登録
        request.setAttribute("task",t);
        request.setAttribute("_token",request.getSession().getId());

        //メッセージIDをセッションスコープに登録
        request.getSession().setAttribute("task_id",t.getId());

        RequestDispatcher rd = request.getRequestDispatcher("jsp/edit.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getServletPath().equals("update.do")){
            String token = (String)request.getParameter("token");
            if(token != null && token.equals(request.getSession().getId())){
                EntityManager em = DBUtil.createEntityManager();

                //セッションスコープからメッセージIDを取得して
                //該当のIDのメッセージ一件のみをデータベースから取得
                Task t = em.find(Task.class,Integer.parseInt(request.getParameter("id")));

                //フォームの内容を各プロパティに上書き
                String content = request.getParameter("content");
                t.setContent(content);

                Timestamp cuurentTime = new Timestamp(System.currentTimeMillis());
                t.setUpdate_at(cuurentTime);

                //データベース更新
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                //セッションスコープ上の不要になったデータを削除
                request.getSession().removeAttribute("task_id");

                //indexページへリダイレクト
                response.sendRedirect(request.getContextPath() + "/index");
            }
        }else if(request.getServletPath().equals("delete.do")){

        }
    }

}
