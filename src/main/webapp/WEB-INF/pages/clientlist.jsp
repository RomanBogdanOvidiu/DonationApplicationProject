<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="Client LIST" />
    <c:param name="body">
    
    <body>
        <h1> ${msg}</h1>
        <p>
       
            <a href='${pageContext.request.contextPath}/client' class="btn btn-info">CREATE</a>
        </p>
        <table
            class="table table-striped table-bordered table-condensed">
            <tr>
                <th>ID</th>
                <th>Address</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>CNP</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td>${(client.id)}</td>
                    <td>${(client.address)}</td>
                    <td>${(client.firstName)}</td>
                    <td>${(client.lastName)}</td>
                    <td>${(client.cnp)}</td>
                    <td><a
                        href='${pageContext.request.contextPath}/client/edit/${client.id}'
                        class="btn btn-primary">edit</a> <a
                        href='${pageContext.request.contextPath}/client/delete/${client.id}'
                        class="btn">delete</a> <a
                        href='${pageContext.request.contextPath}/client/account/${client.id}'
                        class="btn">ManageAccount</a></td>
                        
                </tr>
            </c:forEach>
        </table>

      
        </body>
        
    </c:param>
</c:import>

