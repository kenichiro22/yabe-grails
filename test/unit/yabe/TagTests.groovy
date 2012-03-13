package yabe



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Tag)
class TagTests {

    void testFindOrCreate() {
        Tag tag = Tag.findOrCreateByName("foo")        
        assert tag.name == "foo"
        
        assert tag == Tag.findOrCreateByName("foo")
        assert Tag.count == 1
    }
}
