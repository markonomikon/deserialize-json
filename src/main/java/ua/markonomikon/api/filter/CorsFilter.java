package ua.markonomikon.api.filter;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.io.*;

import static io.smallrye.mutiny.vertx.ReadStreamSubscriber.BUFFER_SIZE;

@Provider
@PreMatching
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

    Logger logger = Logger.getLogger(getClass());

    public static void writeTo(InputStream in, OutputStream out) throws IOException {
        int read;
        final byte[] data = new byte[BUFFER_SIZE];
        while ((read = in.read(data)) != -1) {
            out.write(data, 0, read);
        }
    }

    @Override
    public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx) throws IOException {
        logger.info(requestCtx.getMethod() + " - " + requestCtx.getUriInfo().getPath());
        responseCtx.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseCtx.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");

        responseCtx.getHeaders().add("Access-Control-Max-Age", "1209600");
        responseCtx.getHeaders()
                .add("Access-Control-Allow-Headers",
                        "Authorization, Accept, accept, Accept-Language, authorization, Content-Language, Origin, Content-Type,content-type, X-Requested-With, hostname, Pragma, mobile");
        try {
            MultivaluedMap<String, String> multiValuedMap = requestCtx.getHeaders();
            if (multiValuedMap.containsKey("Origin") && !responseCtx.getHeaders()
                    .containsKey("Access-Control-Allow-Origin")) {
                {
                    responseCtx.getHeaders().add("Access-Control-Allow-Origin", requestCtx.getHeaderString("Origin"));

                }
            }
        } catch (Exception e) {
            responseCtx.getHeaders().add("Access-Control-Allow-Origin", "*");
        }
        if (!requestCtx.getMethod().equals("OPTIONS")) {
            logger.info("-------------------------------------------");
            logger.info("HTTP RESPONSE : " + responseCtx.getEntity());
            logger.info("-------------------------------------------");
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getMethod().equals("OPTIONS")) {
            Response.ResponseBuilder builder = Response.ok();
            requestContext.abortWith(builder.build());
        }
        logger.info("-------------------------------------------");
        logger.info("Path: " + requestContext.getUriInfo().getPath());
        logger.info("Header: " + requestContext.getHeaders());
        logger.info("Method: " + requestContext.getMethod());
        logger.info("Entity: " + getEntityBody(requestContext));
        logger.info("-------------------------------------------");
    }

    private String getEntityBody(ContainerRequestContext requestContext) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = requestContext.getEntityStream();
        final StringBuilder b = new StringBuilder();
        try {
            writeTo(in, out);
            byte[] requestEntity = out.toByteArray();
            if (requestEntity.length == 0) {
                b.append("\n");
            } else {
                b.append(new String(requestEntity)).append("\n");
            }
            requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));
        } catch (IOException ex) {
            //Handle logging error
        }
        return b.toString();
    }

}
