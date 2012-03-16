package yabe

class PostController {

    def index() {
        return [frontPost: Post.list(sort: "postedAt", order: "desc", max: 1).first(),
                 olderPost: Post.list(sort: "postedAt", order: "desc", offset: 1, max: 10)]
    }

    def show(Long id){
        println("show")
        return [post: Post.findById(id)]
    }

    def postComment(String author, String content){
        Post post = Post.findById(params.id)
        Comment c = new Comment(post: post, author: author, content: content, postedAt: new Date())
        c.save(true)
        if(!c.hasErrors()){
            flash.message = "Thanks for posting ${author}!"
            render(view: 'show', model:[post: post])
        }
        else{
            render(view: 'show', model:[post: post, comment: c])
        }
    }

    def listTagged(){
        println("listTagged")
        render(view: 'listTagged', model: [tag: params.tag, posts : Post.findTaggedWith(params.tag)])
    }
}
