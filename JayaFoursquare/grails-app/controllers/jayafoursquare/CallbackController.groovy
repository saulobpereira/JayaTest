package jayafoursquare

import grails.converters.JSON

class CallbackController {

	def foursquareService
	
    def index() { 
		String code = params.code
		
		if(code){
				String accessToken = foursquareService.getAccessToken(code)
				redirect(controller: "wishlist", view:"index", params: [accessToken: accessToken])
				
		}
	}
	
	
}
