package test.jetty.config;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;

// uncomment to enable
//@Component
public class Filter implements javax.servlet.Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } finally {
            try {
                InputStream in = request.getInputStream();
                while (in.read() >= 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
