package Controller.Request;

import Model.Library.TimeKeeper;
import Model.Visitor.VisitorDB;

/**
 * Register visitor request to add visitors into the database.
 *
 * @author Michael Kha
 */
public class RegisterVisitor implements Request{
    /**
     * Message for missing parameters
     */
    private static final String PARAM_MESSAGE = String.format(MISSING_PARAM,
            ARRIVE_REQUEST) + DELIMITER + "first name,last name,address,phone-number";
    /**
     * The Visitor database to add visitor to registered visitors
     */
    private VisitorDB visitorDB;
    /**
     * Allows register action to record the register date
     */
    private TimeKeeper timeKeeper;
    /**
     * The client that made this request
     */
    private String clientID;
    /**
     * Params in the command
     */
    private String params;
    /**
     * The first name
     */
    private String firstName;
    /**
     * The last name
     */
    private String lastName;
    /**
     * The address
     */
    private String address;
    /**
     * The phone number
     */
    private String phoneNumber;

    /**
     * Create a new register visitor request given the visitor database
     * and the parameters for the request.
     * @param timeKeeper The time keeper
     * @param clientID The client making the request
     * @param params The parameters that follow a request command
     */
    public RegisterVisitor(TimeKeeper timeKeeper,
                           String clientID, String params) {
        this.visitorDB = VisitorDB.getInstance();
        this.timeKeeper = timeKeeper;
        this.clientID = clientID;
        this.params = params;
    }

    /**
     * Check the parameters to validate that the request is
     * @return If the parameters are correct
     */
    @Override
    public boolean checkParams() {
        String[] parts = params.split(DELIMITER);
        if (parts.length == 4) {
            firstName = parts[0];
            lastName = parts[1];
            address = parts[2];
            phoneNumber = parts[3];
            return true;
        }
        return false;
    }

    /**
     * Execute the register visitor command which returns a string.
     * @return String containing the results of the book search
     */
    @Override
    public String execute() {
        if (!checkParams()) {
            return clientID + DELIMITER + PARAM_MESSAGE;
        }
        return clientID + DELIMITER + visitorDB.registerVisitor(firstName,
                lastName,address,phoneNumber, timeKeeper.readDate());
    }
}
