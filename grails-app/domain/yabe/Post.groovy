package yabe

class Post {
    String title
    Date postedAt
    String content
    User author

    static hasMany = [comments: Comment]
    static mapping = {
        content type: 'text'
        comments lazy: false
        comments cascade: 'all-delete-orphan'
    }

    static constraints = {
    }
}
