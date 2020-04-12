package com.advancedatabase.project.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Response Time Logger
 * Taken from https://stackoverflow.com/a/42858002/6553042
 */
@Component
@WebFilter("/*")
public class StatsFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // empty
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        long time = System.currentTimeMillis();
        try {
            chain.doFilter(req, resp);
        } finally {
            time = System.currentTimeMillis() - time;
            LOGGER.trace("URI {}: RESPONSE TIME {} ms ", ((HttpServletRequest) req).getRequestURI(),
                    TimeUnit.MILLISECONDS.toSeconds(time));
        }
    }

    @Override
    public void destroy() {
        // empty
    }
}
