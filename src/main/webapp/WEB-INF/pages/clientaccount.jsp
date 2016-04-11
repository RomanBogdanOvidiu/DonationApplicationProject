<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="Account LIST" />
    <c:param name="body">
        <p>
            <a href='${pageContext.request.contextPath}/account/${client}' class="btn btn-info">CREATE ACCOUNT</a>
             <a href='${pageContext.request.contextPath}/bills/${client}' class="btn btn-info">PayBill</a>
        </p>
        <table
            class="table table-striped table-bordered table-condensed">
            <tr>
                <th>ID</th>
                <th>Amount</th>
                <th>Date</th>
                 <th>AccountNumber</th>
                <th>ClientId</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${accounts}" var="account">
                <tr>
                    <td>${(account.id)}</td>
                    <td>${(account.amount)}</td>
                    <td>${(account.date)}</td>
                    <td>${(account.accountNo)}</td>
                    <td>${(account.client.id)}</td>
                    
                    <td><a
                        href='${pageContext.request.contextPath}/client/account/edit/${account.id}'
                        class="btn btn-primary">edit</a> <a
                        href='${pageContext.request.contextPath}/client/account/delete/${account.id}'
                        class="btn">delete</a> <a
                        href='${pageContext.request.contextPath}/client/account/transfer/${account.id}'
                        class="btn">Transfer from this Account</a> 
                        </td>
                        
                      
                </tr>
            </c:forEach>
        </table>

      
        
    </c:param>
</c:import>

