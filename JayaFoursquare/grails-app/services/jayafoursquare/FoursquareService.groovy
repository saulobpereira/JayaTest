package jayafoursquare

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class FoursquareService {

	String foursquareAPIVersion = "20171012"
	
	def getAccessToken(String code){
		String client_id = getProperty("client_id")
		String client_secret = getProperty("client_secret")
		String redirect_uri = getProperty("redirect_uri")
		
		
		String url = "https://foursquare.com/oauth2/access_token"+
		"?client_id=$client_id"+
		"&client_secret=$client_secret"+
		"&grant_type=authorization_code"+
		"&redirect_uri=$redirect_uri"+
		"&code=$code"
		
		def data = JSON.parse( new URL( url ).text )
		String accessToken = data.access_token;
		
		return accessToken
	}
	
	def String getProperty(String property){
		return grails.util.Holders.grailsApplication.config.foursquare."$property"
	}
	
	def User getLoggedUser(String accessToken){
		String urlUserDetails = "https://api.foursquare.com/v2/users/self/?oauth_token=$accessToken&v=$foursquareAPIVersion"
		def dataUserDetails = JSON.parse( new URL( urlUserDetails ).text )
		
		String loggedUserName =  dataUserDetails.response.user.firstName + " "  + dataUserDetails.response.user.lastName
		String prefix = dataUserDetails.response.user.photo.prefix
		String suffix = dataUserDetails.response.user.photo.suffix
		String photoSize = "36x36"
		// prefix + size + suffix
		String photoURL = prefix + photoSize + suffix
		
		User user = new User()
		user.setName(loggedUserName)
		user.setPhotoURL(photoURL)
		return user
	}
	
	def User[] getFriendsRecentVenues(String accessToken){
		String urlRecentCheckins = "https://api.foursquare.com/v2/checkins/recent?oauth_token=$accessToken&v=$foursquareAPIVersion"
		def recentCheckinsData = JSON.parse( new URL( urlRecentCheckins ).text )
		def friends = []
		recentCheckinsData.response.recent.each{ recent->
			User f = new User()
			String userName =  recent.user.firstName + " "  + recent.user.lastName
			f.setName(userName)
			
			String prefix = recent.user.photo.prefix
			String suffix = recent.user.photo.suffix
			String photoSize = "36x36"
			// prefix + size + suffix
			String photoURL = prefix + photoSize + suffix
			f.setPhotoURL(photoURL)
			
			String venueId = recent.venue.id
			String venueName = recent.venue.name
			Venue venue = new Venue()
			venue.setId(venueId)
			venue.setName(venueName)
			venue.setPhotos(getVenuePhotos(venue.id, accessToken))
		
			f.setRecentVenue(venue)
			friends.add(f)
		}
		return friends
	}
	
	def String[] getVenuePhotos(String venueId, String accessToken){
		String urlVenuePhotos = "https://api.foursquare.com/v2/venues/$venueId/photos?oauth_token=$accessToken&v=$foursquareAPIVersion"
		def venuePhotosResponse = JSON.parse( new URL( urlVenuePhotos ).text )
		
		def photos = []
		venuePhotosResponse.response.photos.items.each { item ->
			String venuePhotoURL = item.prefix + "250x250"+item.suffix
			
			photos.add(venuePhotoURL)
		}
//		
//		photos.each{ p ->
//			println "photo " +p
//		}
		
		return photos
		
		
	}
	
}
