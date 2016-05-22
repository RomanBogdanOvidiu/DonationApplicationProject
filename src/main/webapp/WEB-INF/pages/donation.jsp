<%@ page import="com.users.model.Donation"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/common/layout.jsp" charEncoding="UTF-8">
</c:import>

<html>
<body>
<div align="center">
	<form:form method="post"
		action="${pageContext.request.contextPath}/donation/${donation.user.username}"
		modelAttribute="donation" cssClass="form-horizontal">


		<label class="control-label" for="donationsTitle"><strong>Title</strong></label>
		<div class="controls">
			<form:input path="donationsTitle" cssClass="span3"
				cssErrorClass="error" />
		</div>


		<label class="control-label" for="donationsDesc"><strong>Description</strong></label>
		<div class="controls">
			<form:input path="donationsDesc" cssClass="span3" cssErrorClass="error" />
		</div>
		
		<label class="control-label" for="city"><strong>City</strong></label>
		<div class="controls">
			<form:input path="city" cssClass="span3" cssErrorClass="error" />
		</div>


		<input type="submit" class="btn" value="Submit">&nbsp; <a
			href="${pageContext.request.contextPath}/donation/${donation.user.username}"></a>


		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<form:hidden path="donationsId" />



	</form:form>
</div>
</body>
</html>