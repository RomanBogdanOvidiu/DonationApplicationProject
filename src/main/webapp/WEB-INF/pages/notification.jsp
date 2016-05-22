<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
	<c:param name="title" value="Notifications list" />
	<c:param name="body">

		<html>

<head>
<script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script type="text/javascript">
	function poll() {
		$.ajax({
			url : 'check',
			success : function(data) {
				
			data = data;
				$("#div1").html(data);
			}
		});
	}
	setInterval(poll, 5000);
</script>
<!-- <META HTTP-EQUIV="refresh" CONTENT="5; URL=http://localhost:8080/HospitalA3/notification">  -->
</head>
<body>

	<h2><div id="div1"></div></h2>

	
	<table class="table table-striped table-bordered table-condensed">
		<tr>
			<th>ID</th>
			<th>Message</th>
			<th>Patient's Consultation Id</th>
			<th>Status</th>

			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${notification}" var="notification">
			<tr>
				<td>${(notification.id)}</td>
				<td>${(notification.message)}</td>
				<td><div align="center">${(notification.patientId) }</div></td>
				<td>${(notification.status)}</td>
 				
				<td><a
					href='${pageContext.request.contextPath}/client/notification/delete/${notification.id}'
					class="btn btn-primary">delete</a> <a
					href='${pageContext.request.contextPath}/client/notification/check/${notification.patientId}/${notification.id}'
					class="btn btn-primary">Checked </a></td>


			</tr>
		</c:forEach>
	</table>
	<a href='${pageContext.request.contextPath}/' class="btn btn-info">Back</a>
</body>
		</html>
	</c:param>
</c:import>

