package demo1

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/users"(controller:"user",action:"index",method: "GET")   //changes start
        "/users"(controller:"user",action:"save",method: 'POST')
        "/users"(controller:"user",action:"update",method: 'PUT')  //end

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
