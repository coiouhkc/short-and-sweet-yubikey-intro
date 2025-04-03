package me.abratuhi.demo.yubikey.controller;

import io.quarkus.logging.Log;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

@Produces(MediaType.TEXT_HTML)
@Path("/login")
public class YubikeyController {
  public static final String STATUS = "status";

  @Inject
  @Location("pub/login.html")
  Template loginTemplate;

  @Inject
  @Location("pub/result.html")
  Template resultTemplate;

  @GET
  public TemplateInstance display() {
    return loginTemplate.instance();
  }

  @POST
  public TemplateInstance submit(@FormParam("password") final String password) {
    Log.info("Yubikey submitted: " + password);
    String result = eval(password);
    return resultTemplate.instance().data(STATUS, result);
  }

  private String eval(String otp) {
    try (HttpClient client = HttpClient.newHttpClient()) {
      HttpRequest request =
          HttpRequest.newBuilder()
              .uri(
                  URI.create(
                      "https://api.yubico.com/wsapi/2.0/verify?id=1&nonce=1234567890abcdef&otp=%s"
                          .formatted(otp)))
              .GET()
              .build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return Arrays.stream(response.body().split("\n"))
          .filter(line -> line.startsWith("status"))
          .findAny()
          .map(s -> s.split("=")[1])
          .orElseThrow();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
