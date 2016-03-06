package server.logic;


/**
 *
 * Server controller class.
 *
 * All calls from clients are routed through this class that handles all connections to db through the entities and JPA.
 *
 * Created by Johan on 2016-03-04.
 */


public class ServerController {

    private static ServerController controller;

    public ServerController() {

    }

    /**
     * Sets the reference to the controller.
     *
     * @param controllerHandler ServerController.
     */
    public static void setReference(ServerController controllerHandler){
        controller = controllerHandler;
    }

    /**
     * Returns the reference to the controller.
     *
     * @return ServerController
     */
    public static ServerController getController(){
        return controller;
    }

    // METHODS CALLED FROM COMMANDHANDLER

    public boolean checkLogin(String login, String password){
        return true;
    }
}
