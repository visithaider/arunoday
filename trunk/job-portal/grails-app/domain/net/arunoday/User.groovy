package net.arunoday

class User {
	
    static searchable = true
    
    String userId 
	String password 
	String homepage 
	Date dateCreated 
	Profile profile
	
	static constraints = { 
		userId(size:3..20, unique: true) 
		password(size: 6..8) 
		homepage(url: true, nullable: true)
		profile(nullable:true)
	}
	
	static mapping = {
		profile lazy:false
	}
	
    String toString() { 
        "${userId}" 
    } 
	
}
