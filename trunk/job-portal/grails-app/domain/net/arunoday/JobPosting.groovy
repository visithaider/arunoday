package net.arunoday

class JobPosting {
	
	String title
	String skillset
	String companyName
	Integer totalVacancies
	String firstName
	String middleName
	String lastName
	String homePhoneNumber
	String mobilePhoneNumber
	String email
	Experience experience
	//boolean abusive;
	User user
	
    static hasMany = [categories: Category]
	
	static constraints = {
	}
}
