<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Home</title>
	</head>
	<body>
    <g:if test="${frontPost}">
        <div class="post">
            <h2 class="post-title">
                <a href="#">${frontPost.title}</a>
            </h2>
            <div class="post-metadata">
                <span class="post-author">by ${frontPost.author.fullname}</span>
                <span class="post-date">${frontPost.postedAt.format('dd MMM yy')}</span>
                <span class="post-comments">
                    &nbsp;|&nbsp;
                    ${frontPost.comments.size() ?: 'no'}
                    comment
                    <g:if test="${frontPost.comments}">
                     , latest by ${frontPost.comments[0].author}
                    </g:if>
                </span>
            </div>
            <div class="post-content">
                ${frontPost.content.replace("\n", "<br/>")}
            </div>
        </div>

        <g:if test="${olderPost.size() > 1}">
        <div class="older-posts">
            <h3>Older posts <span class="from">from this blog</span></h3>
            <g:each in="${olderPost}" var="oldPost">
            <div class="post">
                <h2 class="post-title">
                    <a href="#">${oldPost.title}</a>
                </h2>
                <div class="post-metadata">
                    <span class="post-author">
                        by ${oldPost.author.fullname}
                    </span>
                    <span class="post-date">
                        ${oldPost.postedAt.format('dd MMM yy')}
                    </span>
                    <div class="post-comments">
                        ${oldPost.comments.size() ?: 'no'}
                        comment
                        <g:if test="${olderPost.comments}">
                        - latest by ${oldPost.comments.toList()[0].author}
                        </g:if>
                    </div>
                </div>
            </div>
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
