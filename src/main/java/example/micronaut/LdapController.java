package example.micronaut;

import io.micronaut.configuration.security.ldap.LdapAuthenticationProvider;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Controller
public class LdapController {

    private final LdapAuthenticationProvider authenticationProvider;

    public LdapController(LdapAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Post("/login")
    public Mono<MutableHttpResponse<?>> login(@Body AuthRequest request) {
        Publisher<AuthenticationResponse> publisher =
                authenticationProvider.authenticate(null, new UsernamePasswordCredentials(request.getUsername(), request.getPassword()));
        return Mono.fromDirect(publisher).map(HttpResponse::ok);
    }
}
