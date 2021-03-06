package Controller.Request;

/**
 * Partial request to represent any request that is non-terminated.
 *
 * @author Michael Kha
 */
public class Partial implements Request {

    /**
     * Execute the partial request by displaying a message.
     * @return A partial request message
     */
    @Override
    public String execute() {
        return PARTIAL_REQUEST + TERMINATOR;
    }

}
