<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Home</title>
	</head>
	<body>
    <g:if test="${frontPost}">
        <g:render template="display" model="[as : 'home', _post: frontPost]"></g:render>
        <g:if test="${olderPost.size() > 1}">
        <div class="older-posts">
            <h3>Older posts <span class="from">from this blog</span></h3>
            <g:each in="${olderPost}" var="oldPost">
                <g:render template="display" model="[as : 'teaser', _post: oldPost]"></g:render>
            </g:each>
        </div>
        </g:if>
    </g:if>
    <g:else>
        <div class="empty">
            There is currently nothing to read here.
        </div>
    </g:else>
	</body>
</html>
