<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${! empty cant}">
		<script>
			alert('${cant}')
		</script>
	</c:when>
</c:choose>
<%
	session.removeAttribute("cant");
%>

<c:choose>
	<c:when test="${! empty can}">
		<script>
			alert('${can}')
		</script>
	</c:when>
</c:choose>
<%
	session.removeAttribute("can");
%>


<style type="text/css">
#T:link {
	color: black;
	text-decoration: none;
	font-family: A Bebedera;
	font-size: 27px;
}

#T:visited {
	color: black;
	text-decoration: none;
}

#T:hover {
	color: black;
	text-decoration: none;
}

.join {
	text-align: right;
	margin: 1cm;
}

#J:link {
	color: gray;
	text-decoration: none;
	font-size: 18px;
}

#J:visited {
	color: gray;
	text-decoration: none;
}

#J:hover {
	color: gray;
	text-decoration: underline;
}

#A {
	font-family: 타이포_쌍문동 굵게;
	font-size: 18px;
}

#Q {
	text-align: right;
	
	
}

#Q:link {
	color: gray;
	text-decoration: none;
	font-family: Suntech;
	font-size: 18px;
}

#Q:visited {
	color: gray;
	text-decoration: none;
}

#Q:hover {
	color: gray;
	text-decoration: underline;
}
</style>


<a href="main" id="T"><h1>&nbsp;See-N</h1></a>



<p class="join">

<a href="board" id="Q">Q & A</a><br><br>


	<c:if test="${empty login_mem and empty login_art }">
		<a href="loginUI" id="J">LOGIN</a>
		<br>
		<a href="joinUI" id="J">JOIN</a>
		<br>
	</c:if>


	<c:if test="${login_mem.userid eq 'admin'}">
		<a href="admin_Firstpage" id="J">관리자 페이지</a>
		<br>
		<a href="loginCheck/logout" id="J">로그아웃</a>
	</c:if>

	<c:choose>
		<c:when test="${! empty login_mem and login_mem.userid ne 'admin'}">
			반갑습니다 ${login_mem.m_username }님!<br><br>
			<a href="mem_FirstMypage" id="J">MyPage</a><br>
			<a href="loginCheck/logout" id="J">로그아웃</a>
		</c:when>

		<c:when test="${! empty login_art}">
			반갑습니다 ${login_art.a_username }님!<br><br>
			<a href="art_FirstMypage" id="J">MyPage</a><br>
			<a href="loginCheck/inputWorkUI" id="J">작품올리기</a><br>
			<a href="loginCheck/logout" id="J">로그아웃</a>
		</c:when>
	</c:choose>

</p>


