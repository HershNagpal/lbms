package Controller.Request;

import Model.Book.BookInfo;
import Model.Library.LibrarySystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Book store search request to query for specific books.
 *
 * @author Michael Kha
 */
public class BookStoreSearch implements Request {

    /**
     * The library
     */
    private LibrarySystem library;
    /**
     * Params in the command
     */
    private String params;
    /**
     * Book title
     */
    private String title;
    /**
     * Authors of the book
     */
    private List<String> authors;
    /**
     * ISBN for the book
     */
    private String isbn;
    /**
     * Publisher for the book
     */
    private String publisher;
    /**
     * Sort ordering
     */
    private String sort;

    /**
     * Create a new book store search request given the book database
     * and the parameters for the request.
     * @param library The library
     * @param params The parameters that follow a request command
     */
    public BookStoreSearch(LibrarySystem library, String params) {
        this.library = library;
        this.params = params;
    }

    /**
     * TODO: proper missing parameter checking
     * Check the parameters to validate that the request is
     * @return If the parameters are correct
     */
    @Override
    public String checkParams() {
        String[] firstSplit = params.split("[{}]");
        authors = new ArrayList<>(Arrays.asList(firstSplit[1].split(",")));
        if (authors.size() == 1 && authors.get(0).equals(IGNORE)) {
            authors.clear();
        }
        String[] titleArr = firstSplit[0].split(",(?!\\s)");
        title = titleArr[0];
        String[] isbnPublisherSort = firstSplit[2].split(",(?!\\s)");
        isbn = isbnPublisherSort[1];
        publisher = isbnPublisherSort[2];
        sort = isbnPublisherSort[3];
        return PROPER_PARAM;
    }

    /**
     * Execute the book store search command which returns a string.
     * @return String containing the results of the book search
     */
    @Override
    public String execute() {
        String check = checkParams();
        if (!check.equals(PROPER_PARAM)) {
            return check;
        }
        Map<String, BookInfo> search = library.bookStoreSearch(title, authors,
                isbn, publisher, sort);
        return buildString(search);
    }

    /**
     * Build the formatted string to return as a response.
     * @param search Mapping of ID to book info
     * @return Readable string representation of the map
     */
    private String buildString(Map<String, BookInfo> search) {
        int size = search.size();
        String result = "" + SEARCH_REQUEST + DELIMITER
                + size + DELIMITER;
        for (String id : search.keySet()) {
            BookInfo book = search.get(id);
            result += NEW_LINE;
            result += id + DELIMITER;
            result += book + DELIMITER;
        }
        result += TERMINATOR;
        return result;
    }

}
