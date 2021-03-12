package example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginSpec {
    
    @Test
    public void ldapLogin() {
        EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer.class);
        HttpClient httpClient = embeddedServer.getApplicationContext().createBean(HttpClient.class, embeddedServer.getURL());
        BlockingHttpClient client = httpClient.toBlocking();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials();
        credentials.setUsername("euler");
        credentials.setPassword("password");
        HttpResponse<AccessRefreshToken> response = client.exchange(HttpRequest.POST("/login", credentials), AccessRefreshToken.class);
        Assertions.assertEquals(HttpStatus.OK, response.status());
        embeddedServer.close();
    }
}
