<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		JSP 템플릿 엔진 이해(~.jsp -> ~.java -> ~.class)
	</div>
	<div class="card-body">
        <%--오늘 날짜: <%= request.getAttribute("strDate") %> <br/>--%>
        오늘 날짜: ${strDate}  <%-- Expression Language(=EL) --%>
    </div>
</div>

<div class="card m-2">
	<div class="card-header">
		객체 저장 범위
	</div>
	<div class="card-body">
        <p>request 범위 객체 값: <%= request.getAttribute("requestScopeValue") %></p>
        <p>session 범위 객체 값: <%= session.getAttribute("sessionScopeValue") %></p>
        <p>application 범위 객체 값: <%= application.getAttribute("applicationScopeValue") %></p>
        
        <hr/>
        <%-- 위와 아래는 같은 코드다. 그럼 ${}로만 명시하는건 어디서... getAttribute를 하는지 컴터가 어케 아는가?
        아래와 같이 탐색순서를 가진다.
        탐색순서: request 범위에서 찾고 없으면 -> session 범위에서 찾고 없으면 -> application 범위에서 찾는다. --%>
        <p>
            request 범위 객체 값: ${requestScopeValue} <br/>

            member's name: ${member.name} <br/>
            member's age: ${member.age} <br/>
            member's job: ${member.job} <br/>
            member's city: ${member.city.name}
        </p>
        <hr/>

        <p>
            session 범위 객체 값: ${sessionScopeValue} <br/>
            
            member2's name: ${member2.name} <br/>
            member2's age: ${member2.age} <br/>
            member2's job: ${member2.job} <br/>
            member2's city: ${member2.city.name}
        </p>
        <hr/>

        <p>
            application 범위 객체 값: ${applicationScopeValue} <br/>
            
            방문 카운팅: ${counter}
        </p>
        
        <hr/>
        <a href="requestScopeSave" class="btn btn-info btn-sm mr-2">request 범위에 객체 저장</a>
        <a href="sessionScopeSave" class="btn btn-info btn-sm mr-2">session 범위에 객체 저장</a>
        <a href="applicationScopeSave" class="btn btn-info btn-sm mr-2">application 범위에 객체 저장</a>


    </div>
</div>

<div class="card m-2">
	<div class="card-header">
		JSTL(Java Standard Tag Library)
	</div>
	<div class="card-body">
        <a href="useJstl1" class="btn btn-info btn-sm mr-2">JSTL 사용하기1</a>
        <a href="useJstl2" class="btn btn-info btn-sm mr-2">JSTL 사용하기2</a>
    </div>
</div>

<div class="card m-2">
	<div class="card-header">
		ModelAndView로 객체 전달
	</div>
	<div class="card-body">
        <a href="modelAndViewReturn" class="btn btn-info btn-sm mr-2">ModelAndView 리턴해서 객체 전달</a>
    </div>
</div>

<div class="card m-2">
	<div class="card-header">
		Model 매개변수로 객체 전달
	</div>
	<div class="card-body">
        <a href="modelArgument" class="btn btn-info btn-sm mr-2">Model 매개변수로 객체 전달</a>
    </div>
</div>


<div class="card m-2">
	<div class="card-header">
		@ModelAttribute로 객체 전달
	</div>
	<div class="card-body">
        <a href="modelAttribute?kind=suit&sex=woman" class="btn btn-info btn-sm mr-2">@ModelAttribute로 객체 전달</a>
    </div>
</div>


<div class="card m-2">
	<div class="card-header">
		Command(DTO) 객체로 전달
	</div>
	<div class="card-body">
        <a href="commandObject?kind=suit&sex=woman" class="btn btn-info btn-sm mr-2">Command(DTO) 객체로 전달</a>
    </div>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>
