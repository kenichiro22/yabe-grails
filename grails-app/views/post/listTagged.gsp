<!doctype html>
<html>
<head>
    <meta name="layout" content="yabe"/>
    <title>Posts tagged with ${tag}</title>
</head>

<body>
<g:if test="${posts.size() > 1}">
    <h3>There are ${posts.size()} posts tagged '${tag}'</h3>
</g:if>
<g:elseif test="${posts}">
    <h3>There is 1 post tagged '${tag}'</h3>
</g:elseif>
<g:else>
    <h3>No post tagged '${tag}'</h3>
</g:else>
<div class="older-posts">
    <g:each in="${posts}" var="post">
        <g:render template="display" model="[_post:post, _as:'teaser']"/>
    </g:each>
</div>
</body>
</html>