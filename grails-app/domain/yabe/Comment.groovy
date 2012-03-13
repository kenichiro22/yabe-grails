package yabe

class Comment {
    String author
    String content
    Date postedAt

    static belongsTo = [post: Post]
    static constraints = {
        author blank: false, nullable: false
        content blank: false, nullable: false
    }
}
