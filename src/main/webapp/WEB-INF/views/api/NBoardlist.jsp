<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="java.util.*, com.bit.model.NBoardDto" %>
{
"list":[
<c:forEach items="${list}" var="row" varStatus="sts">

<c:if test="${!sts.first}">
,
</c:if>
{
"nqid":"${row.nqid}",
"sub":"${row.sub}",
"img":"${row.sub}",
"id":"${row.id}",
"wDate":"${row.wDate}"
}
</c:forEach>
],
"pageMax":${pageMax}
}