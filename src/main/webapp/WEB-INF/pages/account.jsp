<%@ page import="com.users.model.Consultation"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
</c:import>

<html>
<body>
<div align="center">
	<form:form method="post"
		action="${pageContext.request.contextPath}/account/${account.client.id}"
		modelAttribute="account" cssClass="form-horizontal">


		<label class="control-label" for="consultationDetails"><strong>Consultation
				Details</strong></label>
		<div class="controls">
			<form:input path="consultationDetails" cssClass="span3"
				cssErrorClass="error" />
		</div>


		<label class="control-label" for="programare"><strong>ScheduleDate</strong></label>
		<div class="controls">
			<form:input path="programare" cssClass="span3" cssErrorClass="error" />
		</div>


		<input type="submit" class="btn" value="Submit">&nbsp; <a
			href="${pageContext.request.contextPath}/account/${account.client.id}"></a>


		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<form:hidden path="id" />



	</form:form>
</div>
</body>
</html>