package Model.Account;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the accounts of the library by performing operations on the accounts
 * or by using the accounts to perform requests.
 *
 * @author Michael Kha
 */
public class AccountDB implements Serializable {

    /**
     * All the accounts that have been created with usernames as keys
     */
    private Map<String, Account> accounts;

    /**
     * All logged in accounts. The client ID mapped to an account after
     * being logged in. Logging out removes the account from being active.
     */
    private Map<String, Account> activeAccounts;

    /**
     * Create the account database which holds all accounts and active accounts.
     */
    public AccountDB() {
        accounts = new HashMap<>();
        activeAccounts = new HashMap<>();
    }

    /**
     * TODO: check visitor ID exists before calling...to reduce coupling
     * Create an account using the necessary credentials.
     * @param username Username to use
     * @param password Password to use
     * @param role The account role, either visitor or employee
     * @param visitorID The associated visitor ID
     * @return An error or success response about the creation of the account
     */
    public String createAccount(String username, String password, Role role, String visitorID) {
        // Check no duplicate username

        // Check no duplicate visitor ID

        // Create account

        // Return success response
        return null;
    }

    /**
     * Log into an account and associate the client ID to the active account.
     * @param clientID The client ID to log in an account for
     * @param username The username of the account
     * @param password The password of the account
     * @return A response indicating if the login was successful or not
     */
    public String logIn(String clientID, String username, String password) {
        // Check if username and password valid

        // Update active accounts

        // Return success response
        return null;
    }

    /**
     * Log out of an active account that is associated to the client ID.
     * @param clientID The client ID mapped to the account
     * @return Success response for logging out
     */
    public String logOut(String clientID) {
        // Update active accounts;

        // Return success response
        return null;
    }

    /**
     * Undo the last command of the account that is associated to the client ID.
     * @param clientID The client ID to get the account to undo for
     * @return A response indicating if the undo operation could be performed
     */
    public String undo(String clientID) {
        // Grab account and call undo method
        // String response = ...
        return null;
    }

    /**
     * Undo the last undone command of the account that is associated to the client ID.
     * @param clientID The client ID to get the account to undo for
     * @return A response indicating if the redo operation could be performed
     */
    public String redo(String clientID) {
        // Grab account and call undo method
        // String response = ...
        return null;
    }

    /**
     * Set the book info service for an active account.
     * @param clientID The client ID to get the account to set the service for
     * @return A response indicating if the service was set
     */
    public String setBookInfoService(String clientID) {
        // TODO: check what the response should do (request page does not specify)
        // Check account is active

        // Perform setting on account

        // Return success response
        return null;
    }
}