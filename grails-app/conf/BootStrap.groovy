import yabe.User
import yabe.Post
import yabe.Comment

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                initData()
            }
        }
    }
    def destroy = {
    }

    def initData = {
        def users = [
                new User(id: 1, email: "bob@gmail.com", password: "secret", fullname: "Bob", isAdmin: false),
                new User(id: 2, email: "jef@gmail.com", password: "secret", fullname: "Jef", isAdmin: false)]

        users.each {u -> u.save()}

        def posts = [
                new Post(id: 1, title: "About the model layer", content: """
                    The model has a central position in a Play! application. It is the domain-specific
                    representation of the information on which the application operates.

                    Martin fowler defines it as:

                    Responsible for representing concepts of the business, information about the
                    business situation, and business rules. State that reflects the business situation
                    is controlled and used here, even though the technical details of storing it are
                    delegated to the infrastructure. This layer is the heart of business software.
            """, postedAt: Date.parse("yyyy-MM-dd", "2012-01-03"), author: users[0]),
                new Post(id: 2, title: "Just a test of YABE", content: "Well, it's just a test.", postedAt: Date.parse("yyyy-MM-dd", "2012-01-01"), author: users[0]),
                new Post(id: 3, title: "The MVC Application !", content: """
                    A Play! application follows the MVC architectural pattern as applied to the
                    architecture of the Web.

                    This pattern splits the application into separate layers: the Presentation
                    layer and the Model layer. The Presentation layer is further split into a
                    View and a Controller layer.
            """, postedAt: Date.parse("yyyy-MM-dd", "2012-01-02"), author: users[1])
        ]
        posts.each {p -> p.save()}

        def comments = [
                new Comment(author: "Guest", content: "You are right !", postedAt: new Date(), post: posts[0]),
                new Comment(author: "Mike", content: "I knew that ...", postedAt: new Date(), post: posts[0]),
                new Comment(author: "Tom", content: "The post is useless ?", postedAt: new Date(), post: posts[1])
        ]
        comments.each {c -> c.save()}

    }
}
