import entity.Author;
import entity.Book;
import org.hibernate.Session;

import java.util.List;

public class Start {
    public static void main(String[] args) {
        BookHelper bookHelper = new BookHelper();
        AuthorHelper authorHelper = new AuthorHelper();

        //get books
        //bookHelper.getBookList().forEach(b -> System.out.println(b.getName()));

        //remove book id
        //bookHelper.removeBook(4);

        //remove book name
        //bookHelper.removeBook("Какое-то");

        //remove Author
        //authorHelper.removeAuthor(1);

        //add Book
        /*Author author = authorHelper.getAuthor(2);
        bookHelper.addBook(new Book("Какое-то", author));
        bookHelper.addBook(new Book("Ээээх", author));*/


        //get authors
        /*List<Author> authorList = authorHelper.getAuthorList();
        authorList.forEach(System.out::println);*/

        //update
        //authorHelper.updateAuthor("Толстой", "Есенин");

    }
}
