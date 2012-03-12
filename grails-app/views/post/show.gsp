<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${post.title}</title>
</head>

<body>
<ul id="pagination">
    <g:if test="${post.previous()}">
        <li id="previous">
            <g:link action="show" params="[id : post.previous().id]">
                ${post.previous().title}
            </g:link>
        </li>
    </g:if>
    <g:if test="${post.next()}">
        <li id="next">
            <g:link action="post" params="[id : post.next().id]">
                ${post.next().title}
            </g:link>
        </li>
    </g:if>
</ul>
<g:render template="display" model="[_post: post, _as: 'full']"></g:render>
</body>
</html>
