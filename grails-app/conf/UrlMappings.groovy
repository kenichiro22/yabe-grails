class UrlMappings {

	static mappings = {
        "/post/$id/comments"(controller: "post"){
            action = [POST: 'postComment']
        }
        "/post/$id"(controller: "post", action: "show")

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
