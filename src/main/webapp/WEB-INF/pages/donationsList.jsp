<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="Donation LIST" />
	<c:param name="body">
		<html>
<head>
</head>
<body>
	<p>
		<a href='${pageContext.request.contextPath}/account/${client}'
			class="btn btn-info">Search</a>
	</p>
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th>Title</th>
			<th>Donor Name</th>
			<th>City</th>
		</tr>
		<c:forEach items="${donations}" var="donation">
			<tr>
				<td>${(donation.donationsTitle)}</td>
				<td>${(donation.user.username)}</td>
				<td>${(donation.city) }</td>

				<td><a
					href='${pageContext.request.contextPath}/user/donation/view/${donation.donationsId}'
					class="btn btn-primary">  View Details</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href='${pageContext.request.contextPath}/' class="btn btn-info">Back	</a>

</body>
		</html>
	</c:param>
</c:import>

