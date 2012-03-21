<!doctype html>
<html>
<head>
    <meta name="layout" content="yabe"/>
    <title>${post.title}</title>
</head>

<body>
<ul id="pagination">
    <g:if test="${post.previous()}">
        <li id="previous">
            <g:link action="show" params="[id: post.previous().id]">
                ${post.previous().title}
            </g:link>
        </li>
    </g:if>
    <g:if test="${post.next()}">
        <li id="next">
            <g:link action="post" params="[id: post.next().id]">
                ${post.next().title}
            </g:link>
        </li>
    </g:if>
</ul>
<g:if test="${flash.message}">
    <p class="success">${flash.message}</p>
</g:if>
<g:render template="display" model="[_post: post, _as: 'full']"></g:render>

<h3>Post a comment</h3>
<g:form action="postComment" params="[id: post.id]">
    <g:hasErrors bean="${comment}">
        <div class="error">
            <g:renderErrors bean="${comment}"/>
        </div>
    </g:hasErrors>
    <p>
        <label for="author"><g:message code="comment.author.label"/>:</label>
        <g:textField name="author" id="author" value="${comment?.author}"/>
    </p>

    <p>
        <label for="content"><g:message code="comment.content.label"/>:</label>
        <g:textArea name="content" id="content" cols="30" rows="3" value="${comment?.content}"></g:textArea>
    </p>

    <p>
        <input type="submit" value="Submit your comment"/>
    </p>

</g:form>
</body>
</html>
