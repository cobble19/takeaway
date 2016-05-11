package com.cobble.takeaway.oauth2;

import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("myRedirectStrategy")
public final class MyRedirectStrategy implements RedirectStrategy {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private boolean contextRelative;

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        String redirectUrl = calculateRedirectUrl(request.getContextPath(), url);
        redirectUrl = encodeQueryParam(redirectUrl);
        redirectUrl = response.encodeRedirectURL(redirectUrl);
        logger.debug("Redirecting to [{}]", redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    public String encodeQueryParam(String url) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        UriComponents uriComponents = builder.build();
        MultiValueMap<String, String> params = uriComponents.getQueryParams();
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            List<String> encodedValues = new ArrayList<String>(entry.getValue().size());
            for (String value : entry.getValue()) {
                String encodedValue = encodeContent(value);
                encodedValues.add(encodedValue);
            }
            builder.replaceQueryParam(entry.getKey(), encodedValues.toArray());
        }
        return builder.build().toUriString();
    }


    public MultiValueMap<String, String> encodeParam(MultiValueMap<String, String> queryParams) {
        MultiValueMap<String, String> encodedQueryParams =
                new LinkedMultiValueMap<String, String>(queryParams.size());
        for (Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
            List<String> encodedValues = new ArrayList<String>(entry.getValue().size());
            for (String value : entry.getValue()) {
                String encodedValue = encodeContent(value);
                encodedValues.add(encodedValue);
            }
            encodedQueryParams.put(entry.getKey(), encodedValues);
        }
        return encodedQueryParams;
    }

    private String encodeContent(String source) {
        try {
            return URLEncoder.encode(source, CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return source;
    }

    private String calculateRedirectUrl(String contextPath, String url) {
        if (!UrlUtils.isAbsoluteUrl(url)) {
            if (contextRelative) {
                return url;
            } else {
                return contextPath + url;
            }
        }

        // Full URL, including http(s)://

        if (!contextRelative) {
            return url;
        }

        // Calculate the relative URL from the fully qualified URL, minus the scheme and base context.
        url = url.substring(url.indexOf("://") + 3); // strip off scheme
        url = url.substring(url.indexOf(contextPath) + contextPath.length());

        if (url.length() > 1 && url.charAt(0) == '/') {
            url = url.substring(1);
        }

        return url;
    }

    /**
     * If <tt>true</tt>, causes any redirection URLs to be calculated minus the protocol
     * and context path (defaults to <tt>false</tt>).
     */
    public void setContextRelative(boolean useRelativeContext) {
        this.contextRelative = useRelativeContext;
    }
}