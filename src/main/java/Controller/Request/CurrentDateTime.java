package Controller.Request;

import Model.Library.TimeKeeper;

/**
 * Gives the system's current date and time.
 * @author Jack Li
 */
public class CurrentDateTime implements Request {

    /**
     * Used to build a response returned to the user including the simulation's date and time.
     */
    private TimeKeeper timeKeeper;
    /**
     * The client that made this request
     */
    private String clientID;

    /**
     * Creates a new CurrentDateTime command.
     */
    public CurrentDateTime(TimeKeeper timeKeeper, String clientID) {
        this.timeKeeper = timeKeeper;
        this.clientID = clientID;
    }

    /**
     * Executes the CurrentDateTime command to return the current date and time.
     * @return The current date and time within the system.
     */
    @Override
    public String execute() {
        return clientID + DELIMITER + timeKeeper.readTime() +
                DELIMITER + timeKeeper.readDate() + TERMINATOR;
    }
}
