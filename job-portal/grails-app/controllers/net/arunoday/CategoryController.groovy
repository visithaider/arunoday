package net.arunoday

class CategoryController {
	
	static searchable = {
		spellCheck "include"
	}
	
    static navigation = [
                         group: 'tabs',
                         order: 110,
                         action: "list"
    ]
	def scaffold = true
}
