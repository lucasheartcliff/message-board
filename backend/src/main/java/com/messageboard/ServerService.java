package com.messageboard;

import com.messageboard.controller.BaseController;
import com.messageboard.controller.ControllerFactory;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.grizzly.servlet.FilterRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.DispatcherType;
import java.io.IOException;
import java.net.URI;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class ServerService {
  // Base URI the Grizzly HTTP server will listen on
  public static final String BASE_URI = "http://localhost:8080";

  private final ControllerFactory controllerFactory;

  public ServerService(ControllerFactory controllerFactory) {
    this.controllerFactory = controllerFactory;
  }

  /**
   * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
   * application.
   *
   * @return Grizzly HTTP server.
   */
  private HttpServer startServer() {
    // create a resource config that scans for JAX-RS resources and providers
    // in com package
    Set<BaseController> controllers = getControllers();

    ResourceConfig rc = new ResourceConfig().registerInstances(controllers);

    Set<Resource> resources = rc.getResources();

    for (BaseController controller : controllers) {
      rc.register(controller);
    }
    System.out.println("\n\n\n\n\n\n" + resources.toString() + rc.getConfiguration().getResources().toString()
        + rc.getClasses() + rc.getApplication().getClasses().toString() + "\n\n\n\n\n");

    // create and start a new instance of grizzly http server
    // exposing the Jersey application at BASE_URI
    HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc, false);

    System.out.println("Creating static resources...");
    HttpHandler staticHttpHandler = new StaticHttpHandler("WEB-INF/classes/static/");
    server.getServerConfiguration().addHttpHandler(staticHttpHandler,"/");
    WebappContext webappContext = new WebappContext("grizzly web context", "/");
    FilterRegistration filterRegistration = webappContext.addFilter("Jersey", new ServletContainer(rc));
    filterRegistration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), "/*");
    return server;
  }

  public void start() throws IOException {
    final HttpServer server = startServer();



    System.out.println("Starting server...");
    server.start();

    System.out.println(String.format("Jersey app started with endpoints available at "
        + "%s%nHit Ctrl-C to stop it...", BASE_URI));
    System.in.read();
    server.stop();
  }

  private Set<BaseController> getControllers() {
    Set<BaseController> controllerSet = new HashSet<>();

    controllerSet.add(controllerFactory.buildHomeController());
    controllerSet.add(controllerFactory.buildAuthController());
    controllerSet.add(controllerFactory.buildMessageController());
    controllerSet.add(controllerFactory.buildUsersController());
    return controllerSet;
  }
}
