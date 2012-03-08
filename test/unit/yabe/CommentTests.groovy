package yabe



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comment)
@Mock([Post, User])
class CommentTests {

    void testPostComment() {

        // Create a new user and save it
        def bob = new User(email: "bob@gmail.com", password: "secret", fullname : "Bob").save();

        // Create a new post
        def bobPost = new Post(author: bob, title: "My first post", content: "Hello world", postedAt: new Date()).save();

        // Post a first comment
        new Comment(post: bobPost, author: "Jeff", content: "Nice post", postedAt: new Date()).save();
        new Comment(post: bobPost, author: "Tom", content: "I knew that !", postedAt: new Date()).save();

        // Retrieve all comments
        def bobPostComments = Comment.findAllByPost(bobPost)

        // Tests
        assert bobPostComments.size() == 2

        def firstComment = bobPostComments.get(0)
        assert firstComment.author == "Jeff"
        assert firstComment.content == "Nice post"
        assert firstComment.postedAt != null

        def seccondComment = bobPostComments.get(1)
        assert seccondComment.author == "Tom"
        assert seccondComment.content == "I knew that !"
        assert seccondComment.postedAt != null
    }
}
