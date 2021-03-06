package Model.Library;

import Controller.Request.RequestUtil;
import Model.Book.BookDB;
import Model.Book.BookInfo;
import Model.Checkout.CheckoutDB;
import Model.Visitor.VisitorDB;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * The state of the Library when it is closed. Checkouts and visits not allowed.
 *
 * @author Luis Gutierrez
 */
public class LibraryClosed implements LibraryState, RequestUtil {

    /**
     * Returns error string notifying visitor of closed library state.
     * @param search The book search
     * @param visitorID the ID of the visitor checking out the books
     * @param bookIds the isbns of the books to check out
     * @param checkoutDate the current date of checkout
     */
    @Override
    public String checkoutBooks(Map<String, BookInfo> search, LocalDateTime checkoutDate, String visitorID, List<String> bookIds) {
        return BORROW_REQUEST+DELIMITER+CLOSED_LIBRARY+TERMINATOR;
    }

    /**
     * Returns error string notifying visitor of closed library state.
     * @param visitorID the visitor whose visit to start
     * @return a formatted string regarding the success of the operation.
     */
    @Override
    public String beginVisit(String visitorID) {
        return ARRIVE_REQUEST+DELIMITER+CLOSED_LIBRARY+TERMINATOR;
    }
}
