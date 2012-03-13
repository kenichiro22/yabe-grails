package yabe

class Post {
    String title
    Date postedAt
    String content
    User author

    static hasMany = [comments: Comment]
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

}
