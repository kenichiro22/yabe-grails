package yabe

class Comment {
    String author
    String content
    Date postedAt

    static belongsTo = [post: Post]
    static constraints = {
    }
}
