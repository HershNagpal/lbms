package Controller.Request;

import Model.Client.AccountDB;
import Model.Library.TimeKeeper;
import Model.Visitor.VisitorDB;

/**
 * End visit request to start a visit for a visitor.
 *
 * @author Michael Kha
 */
public class EndVisit implements Request {
    /**
     * Message for missing parameters
     */
    private static final String PARAM_MESSAGE = String.format(MISSING_PARAM,
            ARRIVE_REQUEST) + DELIMITER + "visitor ID";
    /**
     * Visitor database used to update currentVisitors by removing visitor ending their visit.
     */
    private VisitorDB visitorDB;
    /**
     * Account database to retrieve visitor ID from account if not provided
     */
    private AccountDB accountDB;
    /**
     * TimeKeeper used to build response to user by including the simulation date and time,
     * which represent the time the visitor left the library.
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
     * The visitor ID to start the visit for
     */
    private String visitorID;

    /**
     * Create a new end visit request given the visitor database
     * and the parameters for the request.
     * TODO finish commenting request, LBServer, request parser classes
     * @param timeKeeper TimeKeeper
     * @param clientID The client making the request
     * @param params The parameters that follow a request command
     */
    public EndVisit(TimeKeeper timeKeeper, String clientID, String params) {
        this.visitorDB = VisitorDB.getInstance();
        accountDB = AccountDB.getInstance();
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
        //visitorID given
        if (parts.length == 1) {
            visitorID = parts[0];
            return true;
        }
        //visitorID not given
        else if (parts.length == 0) {
            visitorID = accountDB.getVisitorIDFromClientID(clientID);
            return true;
        }
        return false;
    }

    /**
     * Execute the begin visit command which returns a string.
     * @return String indicating that the visit has ended and the end time
     *         and duration of the visit.
     */
    @Override
    public String execute() {
        if (!checkParams()) {
            return clientID + DELIMITER + PARAM_MESSAGE;
        }
        String response = clientID + DELIMITER + visitorDB.endVisit(visitorID,
                timeKeeper.getClock(), timeKeeper.readTime());
        String[] parts = response.split(",");
        //Only add successful endVisit requests to account commandHistory
        if(parts.length == 4){
            accountDB.addRequestToCommandHistory(this, clientID);
        }
        return response;
    }
}
