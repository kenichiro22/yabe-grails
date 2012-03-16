package yabe

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.hibernate.FetchMode

class PostTagTests extends GroovyTestCase {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testTagCloud() {
        def bob = new User(email: "bob@gmail.com", password: "secret", fullname: "Bob").save()
        def bobPost = new Post(content: "Hello world", title: "My first post", postedAt: new Date(), author: bob).save()
        def anotherBobPost = new Post(content: "Hello world", title: "My second post", postedAt: new Date(), author: bob).save()

        // Well
        assert Post.findTaggedWith("Red").size() == 0

        // Tag it now
        bobPost.tagItWith("Red").tagItWith("Blue").save()
        anotherBobPost.tagItWith("Red").tagItWith("Green").save()

        def cloud = Post.getTagCloud()
        assert cloud.size() == 3
        assert cloud.get(0)['tag'] == 'Blue'
        assert cloud.get(0)['pound'] == 1
        assert cloud.get(1)['tag'] == 'Green'
        assert cloud.get(1)['pound'] == 1
        assert cloud.get(2)['tag'] == 'Red'
        assert cloud.get(2)['pound'] == 2
//        println Post.createCriteria().list {
//            projections {
//                count('id', "pound")
//                property("tags.name", "tag")
//                groupProperty("tags.name")
//            }
//            join("tags")
//            fetchMode("tags", FetchMode.JOIN)
//            order("tags.name")
//        }
    }
}
