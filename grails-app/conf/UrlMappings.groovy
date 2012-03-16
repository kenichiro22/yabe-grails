class UrlMappings {

	static mappings = {
        "/posts/$id/comments"(controller: "post"){
            action = [POST: 'postComment']
        }
        "/posts/$id"(controller: "post", action: "show"){
            constraints {
                id(matches: /\d+/)
            }
        }
        "/posts/$tag"(controller: "post", action: "listTagged")

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
