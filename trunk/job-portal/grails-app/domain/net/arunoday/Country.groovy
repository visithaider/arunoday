package net.arunoday


class Country {

    static searchable = {
        spellCheck "include"
    }

    String code 
    String name
    
    static constraints = {
    }
    
    String toString() { 
        "${name}" 
    } 
    
}
