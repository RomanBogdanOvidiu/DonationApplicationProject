<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="Account LIST" />
	<c:param name="body">
		<p>
			<a href='${pageContext.request.contextPath}/account/${client}'
				class="btn btn-info">Add consultation</a>
		</p>
		<table class="table table-striped table-bordered table-condensed">
			<tr>
				<th>ID</th>
				<th>Consultation Details</th>
				<th>Schedule Date</th>
				<th>Consultation Number</th>
				<th>Client Id</th>
				<th>Patient treated?</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${accounts}" var="account">
				<tr>
					<td>${(account.id)}</td>
					<td>${(account.consultationDetails)}</td>
					<td>${(account.programare)}</td>
					<td>${(account.consultationNo)}</td>
					<td>${(account.client.id)}</td>
					<td>${account.checked}</td>

					<td><a
						href='${pageContext.request.contextPath}/client/account/edit/${account.id}'
						class="btn btn-primary">edit</a> <a
						href='${pageContext.request.contextPath}/client/account/delete/${account.id}'
						class="btn btn-primary">delete</a> <a
						href='${pageContext.request.contextPath}/client/account/notifyDoctor/${account.id}'
						class="btn btn-primary">NotifyDoctor </a></td>


				</tr>
			</c:forEach>
		</table>
		<a href='${pageContext.request.contextPath}/clientlist/'
			class="btn btn-info">Back</a>

	</c:param>
</c:import>

