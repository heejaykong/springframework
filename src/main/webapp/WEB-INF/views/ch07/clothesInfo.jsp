<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		ModelAttribute를 이용한 데이터 전달
	</div>
	<div class="card-body">
		<p>clothes kind: ${kind}</p>
		<p>clothes sex: ${sex}</p>
		
		<hr/>
		
		<%-- <p>clothes kind: ${ch07cloth.kind}</p>
		<p>clothes sex: ${ch07cloth.sex}</p>
		
		<hr/>--%>
		
		<p>clothes kind: ${cloth.kind}</p>
		<p>clothes sex: ${cloth.sex}</p>
		
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
