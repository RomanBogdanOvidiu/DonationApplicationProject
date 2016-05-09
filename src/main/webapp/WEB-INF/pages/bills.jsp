<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
    <c:param name="title" value="Account LIST" />
    <c:param name="body">
      
        <table
            class="table table-striped table-bordered table-condensed">
            <tr>
                <th>ID</th>
                <th>BillName</th>
                <th>AmountToPay</th>
                <th>ClientId </th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${bills}" var="bills">
                <tr>
                    <td>${(bills.id)}</td>
                    <td>${(bills.billName)}</td>
                    <td>${(bills.billCost)}</td>
                    <td>${bills.clientId }</td>
                    
                    
                    <td><a
                        href='${pageContext.request.contextPath}/paybill/${bills.id}/${bills.clientId}/${bills.billCost}'
                        class="btn btn-primary">Pay</a>
                        </td>
                        
                      
                </tr>
            </c:forEach>
        </table>

      
        
    </c:param>
</c:import>

