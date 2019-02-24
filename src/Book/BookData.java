package Book;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Book info data structure that holds books to be searched for.
 *
 * @author Michael Kha
 */
public abstract class BookData {

    /**
     * Available books mapped to their IBSNs
     */
    Map<String, BookInfo> books;
    /**
     * Books produced by the last search and mapped to an ID
     */
    Map<String, BookInfo> lastSearch;
    /**
     * Comparators for book info
     */
    private Comparator<BookInfo> byTitle;
    private Comparator<BookInfo> byPublishDate;
    private Comparator<BookInfo> byCopies;

    /**
     * Create a new book data structure that can be used to search for books.
     */
    public BookData() {
        books = new HashMap<>();
        byTitle = new TitleComparator();
        byPublishDate = new PublishDateComparator();
        byCopies = new CopiesComparator();
    }

    /**
     * Search the books using a filter on all the available books for purchase.
     * @param title Title search parameter
     * @param authors Authors search parameter
     * @param isbn ISBN search parameter
     * @param publisher Publisher search parameter
     * @param sort Sort the search by either title or publish-date
     * @return The mapping of hits to a unique ID
     */
    public Map<String, BookInfo> searchBooks(String title,
                                              List<String> authors,
                                              String isbn,
                                              String publisher, String sort) {
        Map<String, BookInfo> searchedBooks = new HashMap<>();
        // Filter out results into a list of search hits
        List<BookInfo> hits = books.values().stream()
                .filter(b -> matchingFilter(b, title, authors,
                        isbn, publisher))
                .collect(Collectors.toList());
        // Sort the books by the specified parameter
        switch (sort) {
            case "*":
                break;
            case "title":
                hits.sort(byTitle);
                break;
            case "publish-date":
                hits.sort(byPublishDate);
                break;
            case "book-status":
                hits.sort(byCopies);
                hits = hits.stream().filter(BookInfo::hasCopiesAvailable)
                        .collect(Collectors.toList());
                break;
            default:
                // TODO: throw an invalid parameter exception
        }

        // Map to a unique ID for the hits
        int id = 0;
        for (BookInfo info : hits) {
            searchedBooks.put(String.valueOf(id), info);
            id++;
        }
        // Set last search to this recent search
        lastSearch = searchedBooks;
        return searchedBooks;
    }

    /**
     * Determines a matching between book info and its search parameters.
     * Parameters are ignored if they equal '*'. Authors are ignored if the
     * list is empty.
     * @param book Book info to compare with
     * @param title Title from search
     * @param authors Authors from search
     * @param isbn ISBN from search
     * @param publisher Publisher from search
     * @return If the book info has completely matched through each filter
     */
    private boolean matchingFilter(BookInfo book, String title,
                                   List<String> authors,
                                   String isbn, String publisher) {
        String bookTitle = book.getTitle();
        List<String> bookAuthors = book.getAuthors();
        String bookIsbn = book.getIsbn();
        String bookPublisher = book.getPublisher();
        String ignore = "*";
        if (!title.equals(ignore)) {
            // Title may only contain a substring
            if (!bookTitle.contains(title)) {
                return false;
            }
        }
        if (!authors.isEmpty()) {
            for (String author : bookAuthors) {
                // Authors must exactly match
                if (!authors.contains(author)) {
                    return false;
                }
            }
        }
        if (!isbn.equals(ignore)) {
            // ISBN must exactly match
            if (!bookIsbn.equals(isbn)) {
                return false;
            }
        }
        if (!publisher.equals(ignore)) {
            // Publisher must exactly match
            if (!bookPublisher.equals(publisher)) {
                return false;
            }
        }
        return true;
    }
}