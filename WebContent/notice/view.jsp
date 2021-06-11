<!-- template.html -->
<%@page import="kr.or.kpc.dto.NoticeDto"%>
<%@page import="kr.or.kpc.dao.NoticeDao"%>
<%@ page pageEncoding="utf-8" %>
<%@ include file="../inc/header.jsp" %>
<%
	String tempPage = request.getParameter("page");
	String tempNum = request.getParameter("num");
	int cPage = 0;
	int num = 0;
	if(tempPage== null || tempPage.length()==0){
		cPage = 1;
	}
	try{
		cPage = Integer.parseInt(tempPage);
	}catch(NumberFormatException e){
		cPage = 1;
	}
	
	if(tempNum == null || tempNum.length()==0){
		num = -1;
	}
	
	try{
		num = Integer.parseInt(tempNum);
	}catch(NumberFormatException e){
		num = -1;
	}
	
	NoticeDao dao = NoticeDao.getInstance();
	NoticeDto dto = dao.select(num);//
	
	if(dto == null){
		num = -1;
	}
	
	if(num == -1){
%>
	<script>
		alert('해당글이 존재 하지않습니다.');
		location.href="list.jsp?page=<%=cPage%>";
	</script>
<%}%>
  	<!-- breadcrumb start -->
  	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="/index.jsp">홈</a></li>
	    <li class="breadcrumb-item">공지사항</li>
	 </ol>
	</nav>
	<!-- breadcrumb end -->
  	
  	<!-- container start -->
	<div class="container">
		<!-- col start -->
		<div class="row">
			<div class="col-md-12">
				<h5>공지사항 보기</h5>
				<%-- form start --%>
				<form name="noticeForm" method="post" action="saveDb.jsp">
				  <div class="form-group">
				     작성자 : <%=dto.getWriter() %>
				  </div>
				  <div class="form-group">
				     날짜  : <%=dto.getRegdate() %>
				  </div>
				  <div class="form-group">
				    제목 : <%=dto.getTitle() %>
				  </div>
				  <div class="form-group">
				  	내용 : <%=dto.getContent() %>
				  </div>
				  
				</form>
				<div class="text-right">
					<a class="btn btn-secondary" href="list.jsp" 
					role="button">리스트</a>
					<a class="btn btn-success" href="modify.jsp" 
					role="button">수정</a>
					<a class="btn btn-danger" id="deleteNotice"
					role="button">삭제</a>
				</div>
				<%-- form end --%>
			</div>
		</div>
		<!-- col end -->
	</div>
	<!-- container end -->
<%@ include file="../inc/footer.jsp" %>
	





