package yabe

class PostController {

    def index() {
        return [frontPost: Post.list(sort: "postedAt", order: "desc", max: 1).first(),
                 olderPost: Post.list(sort: "postedAt", order: "desc", offset: 1, max: 10)]
    }
}
