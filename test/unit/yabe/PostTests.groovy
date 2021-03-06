package yabe



import grails.test.mixin.*
import org.hibernate.FetchMode

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Post)
class PostTests {

    void testGetPrevNext() {
        mockDomain(User)

        def bob = new User(email: "bob@gmail.com", password: "secret", fullname: "Bob").save()
        def bobPost3 = new Post(content: "Hello world", title: "My third post", postedAt: Date.parse("yyyy-MM-dd", "2012-01-03"), author: bob).save()
        def bobPost2 = new Post(content: "Hello world", title: "My second post", postedAt: Date.parse("yyyy-MM-dd", "2012-01-02"), author: bob).save()
        def bobPost1 = new Post(content: "Hello world", title: "My first post", postedAt: Date.parse("yyyy-MM-dd", "2012-01-01"), author: bob).save()

        assert bobPost3.previous().title == "My second post"
        assert bobPost3.next() == null

        assert bobPost2.previous().title == "My first post"
        assert bobPost2.next().title == "My third post"

        assert bobPost1.next().title == "My second post"
        assert bobPost1.previous() == null

    }

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

    void testFindTaggedWith() {
        mockDomain(User)
        mockDomain(Tag)

        def bob = new User(email: "bob@gmail.com", password: "secret", fullname: "Bob").save()
        def bobPost = new Post(content: "Hello world", title: "My first post", postedAt: new Date(), author: bob).save()
        def anotherBobPost = new Post(content: "Hello world", title: "My second post", postedAt: new Date(), author: bob).save()

        // Well
        assert Post.findTaggedWith("Red").size() == 0

        // Tag it now
        bobPost.tagItWith("Red").tagItWith("Blue").save()
        anotherBobPost.tagItWith("Red").tagItWith("Green").save()

        // Check
        assert Post.findTaggedWith("Red").size() == 2
        assert Post.findTaggedWith("Blue").size() == 1
        assert Post.findTaggedWith("Green").size() == 1
    }
}
