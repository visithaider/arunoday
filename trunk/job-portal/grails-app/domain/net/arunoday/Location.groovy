package net.arunoday


class Location {

    static searchable = true
    
    String city
    Country country
    
    static constraints = {
        country(nullable: false) 
    }
    
    static mapping = {
        country lazy:false
    }
    
    String toString() { 
        "${city}" 
    } 
    
}
