<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>タスク一覧</h2>
        <ul>
                <table border="1">
                    <tr>
                        <td>
                           <p>ID</p>
                        </td>
                        <c:forEach var="task" items="${task}">
                        <td>
                            <a href="${pageContext.request.contextPath}/show?id=${task.id}">
                            <c:out value="${task.id}" />
                            </a>
                        </td>
                        </c:forEach>
                    </tr>
<!--                     <tr> -->
<!--                         <td> -->
<!--                            <p>作成日時</p> -->
<!--                         </td> -->
<%--                         <c:forEach var="task" items="${task}"> --%>
<!--                         <td> -->
<%--                             <c:out value="${task.create_at}" /> --%>
<!--                         </td> -->
<%--                         </c:forEach> --%>
<!--                     </tr> -->
<!--                     <tr> -->
<!--                         <td> -->
<!--                            <p>更新日時</p> -->
<!--                         </td> -->
<%--                         <c:forEach var="task" items="${task}"> --%>
<!--                         <td> -->
<%--                             <c:out value="${task.update_at}" /> --%>
<!--                         </td> -->
<%--                         </c:forEach> --%>
<!--                     </tr> -->
                    <tr>
                        <td>
                           <p>タスク内容</p>
                        </td>
                        <c:forEach var="task" items="${task}">
                        <td>
                            <c:out value="${task.content}" />
                        </td>
                        </c:forEach>
                    </tr>
                </table>
        </ul>

        <p><a href="${pageContext.request.contextPath}/new">新規タスクの投稿</a></p>

    </c:param>
</c:import>