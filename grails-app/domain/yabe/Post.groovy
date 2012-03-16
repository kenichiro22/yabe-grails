package yabe

class Post {
    String title
    Date postedAt
    String content
    User author

    static hasMany = [comments: Comment, tags: Tag]
    static mapping = {
        content type: 'text'
//        comments lazy: false
        comments cascade: 'all-delete-orphan'
    }

    static constraints = {
    }

    Post previous() {
        def query = Post.where {
            lt('postedAt', this.postedAt)
        }

        return query.find(sort: 'postedAt', order: 'desc', max: 1) ?: null
    }

    Post next() {
        def query = Post.where {
            gt('postedAt', this.postedAt)
        }
        return query.find(sort: 'postedAt', order: 'asc', max: 1) ?: null
    }

    Post tagItWith(String tag) {
//        tags.add(Tag.findOrCreateByName(tag))
//        return this
        return addToTags(Tag.findOrCreateByName(tag))
    }

    static List findTaggedWith(String tag) {
        def c = Post.createCriteria()
        return c.list {
            tags {
                eq("name", tag)
            }
        }
    }

    static List<Map> getTagCloud() {
        return Post.executeQuery("select new map(t.name as tag, count(p.id) as pound) from Post p join p.tags as t group by t.name order by t.name")
    }

}
