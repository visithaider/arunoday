package net.arunoday.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * 
 * @author Aparna Chaudhary (aparna.chaudhary@gmail.com)
 */
@StaticMetamodel(net.arunoday.entity.Book.class)
public class Book_ {
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, String> isbn;
}
