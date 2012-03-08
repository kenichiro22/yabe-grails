package yabe



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

    void testCreateAndRetrieveUser() {
        // Create a new author and save it
        new User(email : "bob@gmail.com", password : "secret", fullname :"Bob").save();

        // Retrieve the author with e-mail address bob@gmail.com
        def bob = User.findByEmail("bob@gmail.com")

        // Testa
        assert bob != null
        assert bob.fullname == "Bob"
    }
}
