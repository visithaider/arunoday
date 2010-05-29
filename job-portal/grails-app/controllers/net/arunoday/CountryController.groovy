package net.arunoday

class CountryController {
	
	def scaffold = Country
	
	static navigation = [
	                     group: 'tabs',
	                     order: 110,
	                     action: "list"
	]
	
}
