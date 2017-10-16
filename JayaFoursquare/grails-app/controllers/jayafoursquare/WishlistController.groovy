package jayafoursquare

import grails.converters.JSON

class WishlistController {

	def foursquareService
	
    def index() {
		String accessToken = params.accessToken
		
		User user = foursquareService.getLoggedUser(accessToken)
		
		User[] friends = foursquareService.getFriendsRecentVenues(accessToken)
		
		
		render(view:'index', model:[loggedUser:user, friends:friends])
		
	}
}
