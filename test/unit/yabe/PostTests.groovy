package yabe



import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Post)
class PostTests {

    void testUseTheCommentsRelation() {
        mockDomain(User)
        mockDomain(Comment)

        def bob = new User(email: "bob@gmail.com", password: "secret", fullname: "Bob").save()
        def bobPost = new Post(content: "Hello world", title: "My first post", postedAt: new Date(), author: bob).save()

        // Post a first comment
        bobPost.addToComments(author: "Jeff", content: "Nice post");
        bobPost.addToComments(author: "Tom", content: "I knew that !");
        bobPost.save()

        // Count things
        assert User.count == 1
        assert Post.count == 1
        assert Comment.count == 2

        // Retrieve Bob's post
        bobPost = Post.findAllByAuthor(bob).first()

        // Navigate to comments
        assert bobPost.comments.size() == 2
        assert bobPost.comments.toList().get(0).author == "Jeff"

        // Delete the post
        bobPost.delete(flush: true)

        // Check that all comments have been deleted
        assert User.count == 1
        assert Post.count == 0
        // TODO: delete cascade doesn't work
//        assert Comment.count == 0
    }

    void testCreatePost() {
        mockDomain(User)

        // Create a new author and save it
        def bob = new User(email: "bob@gmail.com", password: "secret", fullname: "Bob").save()

        // Create a new post
        new Post(content: "Hello world", title: "My first post", postedAt: new Date(), author: bob).save()

        // Test that the post has been created
        assert Post.count == 1

        // Retrieve all posts created by Bob
        def bobPosts = Post.findAllByAuthor(bob)

        // Tests
        assert bobPosts.size() == 1

        def firstPost = bobPosts.get(0)
        assert firstPost.title == "My first post"
        assert firstPost.content == "Hello world"
        assert firstPost.postedAt != null
        assert firstPost.author == bob
    }
}
