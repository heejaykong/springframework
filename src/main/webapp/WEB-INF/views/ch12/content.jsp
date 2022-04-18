<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card">
	<div class="card-header">
		ViewName을 객체 이름으로 해석
	</div>
	<div class="card-body">
		<h6>다운로드한 파일 목록(<span id="totalFileNum" class="text-danger">0</span>)</h6>
		<div id="fileList"></div>
	</div>
	<script>
		$(function() { // html을 다 파싱하면 비동기 ajax를 실행해라.
			$.ajax({
				url: "fileList"
			}).done(data => {
			// console.log(data);
			// {"totlaFileNum":20, "fileList":["a.jpg", "b.jpg", ...]} 형태로 들어올거임.
				let ulTag = "<ul>";
				for(const filename of data.fileList) {
					ulTag += "<li>";
					ulTag += "	<a href='fileDownload?filename=" + filename + "'>" + filename + "</a>";
					ulTag += "</li>";
				}
				ulTag+= "</ul>";
				$("#totalFileNum").html(data.totlaFileNum);
				$("#fileList").html(ulTag);
			});
		});
	</script>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>