package Request;

public class ReturnBook implements Request {
    @Override
    public boolean checkParams(String params) {
        return false;
    }

    @Override
    public String execute() {
        return null;
    }
}
