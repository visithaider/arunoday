package net.arunoday


class Experience {
	
	Integer minYears
	Integer maxYears
	Country country
	
	static constraints = {
		country(nullable: false) 
	}
	
	static mapping = {
		country lazy:false
	}
	
    String toString() { 
        "${minYears} - ${maxYears}" 
    } 
	
}
