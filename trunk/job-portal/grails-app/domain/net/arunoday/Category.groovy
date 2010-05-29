package net.arunoday


class Category {
	
	static searchable = true
	
	String description
	Country country
	//static belongsTo = JobPosting
	
	
//	static constraints = {
//		country(nullable: false) 
//	}
//	
//	static mapping = {
//		country lazy:false
//	}
	
	String toString() { 
		"${description}" 
	} 
	
}
