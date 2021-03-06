import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class HeaderFilter implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext cres) throws IOException {
        String allowedOrigin;

        if((allowedOrigin = requestContext.getHeaderString("Origin")) == null) {
            allowedOrigin = "*";
        }
        cres.getHeaders().add("Access-Control-Allow-Origin", allowedOrigin);
        cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
        cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    }
}
