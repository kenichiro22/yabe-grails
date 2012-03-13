package yabe

class Tag {
    static belongsTo = Post
    static hasMany = [posts: Post]
    static constraints = {
        name nullable: false, blank: false
    }

    static Tag findOrCreateByName(String name){
        Tag tag = Tag.findByName(name)
        if(tag == null) {
            tag = new Tag(name: name).save();
        }
        return tag
    }

    String name
}
