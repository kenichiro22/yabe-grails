<div class="post ${_as == 'teaser' ? 'teaser' : ''}">
    <h2 class="post-title">
        <g:link controller="post" action="show" params="[id:_post.id]">${_post.title}</g:link>
    </h2>
    <div class="post-metadata">
        <span class="post-author">by ${_post.author.fullname}</span>,
        <span class="post-date">${_post.postedAt.format('dd MMM yy')}</span>
        <g:if test="${_as == 'full'}">
            <span class="post-comments">
                &nbsp;|&nbsp;
                ${_post.comments.size() ?: 'no'}
                comment
                <g:if test="${_post.comments}">
                    , latest by ${_post.comments.toList().get(0).author}
                </g:if>
            </span>
            <g:if test="${_post.tags}">
                <span class="post-tags">
                    - Tagged
                    <g:each in="${post.tags}" var="tag">
                        <g:link action="listTagged" params="[tag : tag.name]">
                            ${tag.name}
                        </g:link>
                    </g:each>
                </span>
            </g:if>
        </g:if>
    </div>
    <g:if test="${_as != 'teaser'}">
        <div class="post-content">
            ${_post.content.encodeAsHTML().replace("\n", "<br/>")}
        </div>
    </g:if>
</div>
<g:if test="${_as == 'full'}">
    <div class="comments">
        <h3>
            ${_post.comments.size() ?: 'no'}
            comments
        </h3>
        <g:each in="${_post.comments}" var="comment">
            <div class="comment">
                <div class="comment-metadata">
                    <span class="comment-author">by ${comment.author},</span>
                    <span class="comment-date">
                        ${comment.postedAt.format('dd MMM yy')}
                    </span>
                </div>

                <div class="comment-content">
                    <div class="about">Detail:</div>
                    ${comment.content.encodeAsHTML().replace("\n", "<br/>")}
                </div>
            </div>
        </g:each>
    </div>
</g:if>
