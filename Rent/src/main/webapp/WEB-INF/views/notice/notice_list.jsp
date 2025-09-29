<!-- URL : /notice/list -->

<%@page import="com.rent.vaca.notice.NoticeVO"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header_nosearchbar.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        header{
        	margin-bottom:50px;
        }
        section a{
            text-decoration:none;
            color:inherit;
        }
        section a:hover{
            text-decoration:underline;
        }
        .pageTitle{
            width:1024px;
            margin:auto;
        }
        section h1{
            display:inline-block;
        }
        .write-btn{
            position:relative;
            left:70%;
            bottom: 10px;
        }
        .listArea table{
            table-layout: fixed;
            width:1024px;
            margin:50px auto;
        }
        .listArea tr{
            font-size:1.5em;
            border-bottom:1px solid var(--bs-orange);
        }
        .listArea tr:first-child{
            border-top:1px solid var(--bs-orange);
        }
        .listArea td{
            padding:20px 5px;
        }
        .listArea .title{
            width:80%;
            overflow:hidden;
            text-overflow:ellipsis;
            white-space:nowrap;
        }
        .listArea .space{
            width:5%;
        }
        .listArea .wdate{
            width:15%;
            color:gray;
        }
	    footer{
	    	margin-top:150px;
	    }
    </style>
    <script>
        $(function(){
            $(".page-item").on( "click", function() {
                console.log(this);
            } );
        });
    </script>
</head>
<body>

    <section>
    <div class="pageTitle">
        <h1>공지사항</h1>
        <c:if test='${not empty sessionScope.user && sessionScope.user.grade == "A"}'>
        	<button type="button" onclick="location.href='<c:url value="/notice/write" />'" class="btn btn-primary write-btn">글쓰기</button>
        </c:if>
    </div> <!-- end:pageTitle -->
    <div class="listArea">
        <table>
        <c:forEach var="notice" items="${noticeList}">
            <tr>
                <td class="title">
                    <a href="<c:url value="/notice/view/${notice.noticeNo}" />">
                    	${notice.title}
                    </a>
                </td>
                <td class="space"></td>
                <td class="wdate">
                    ${notice.wdate}
                </td>
            </tr>
           </c:forEach>
        </table>
    </div> <!-- end:listArea -->
    <!-- <div class="Page">
        <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="#">&lt;</a></li>
            <li class="page-item active"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item disabled"><a class="page-link" href="#">&gt;</a></li>
        </ul>
    </div> end:Page -->
    </section>
</body>
</html>
<%@ include file="../include/footer.jsp"%>