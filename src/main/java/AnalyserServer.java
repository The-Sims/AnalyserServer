import clienthandler.ClientHandler;
import clienthandler.IClientHandler;
import communication.messagegenerator.IMessageGenerator;
import communication.messagegenerator.MessageGenerator;
import communication.messagehandlers.IMessageHandlerFactory;
import communication.messagehandlers.MessageHandlerFactory;
import communication.messageprocessor.IMessageProcessor;
import communication.messageprocessor.MessageProcessor;
import communication.websockets.ServerWebsocket;
import logger.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

public class AnalyserServer {

    private static final int PORT = 8090;


    public static void main(String[] args){

        final ServerWebsocket socket = new ServerWebsocket();
        IMessageGenerator messageGenerator = new MessageGenerator(socket);

        IMessageHandlerFactory factory = new MessageHandlerFactory();
        IClientHandler handler = new ClientHandler(messageGenerator);
        IMessageProcessor messageHandler = new MessageProcessor(factory, handler);


        socket.setMessageHandler(messageHandler);

        Server webSocketServer = new Server();
        ServerConnector connector = new ServerConnector(webSocketServer);
        connector.setPort(PORT);
        webSocketServer.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler webSocketContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        webSocketContext.setContextPath("/");
        webSocketServer.setHandler(webSocketContext);

        try {
            // Initialize javax.websocket layer
            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(webSocketContext);

            // Add websocket endpoint to javax.websocket layer
            ServerEndpointConfig config = ServerEndpointConfig.Builder.create(socket.getClass(), socket.getClass().getAnnotation(ServerEndpoint.class).value())
                    .configurator(new ServerEndpointConfig.Configurator() {
                        @Override
                        public <T> T getEndpointInstance(Class<T> endpointClass) {
                            return (T) socket;
                        }
                    })
                    .build();
            wscontainer.addEndpoint(config);
            webSocketServer.start();
            webSocketServer.join();

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
    }
}
