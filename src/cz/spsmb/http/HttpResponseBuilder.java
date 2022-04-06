package cz.spsmb.http;

import java.util.HashMap;
import java.util.Map;

public class HttpResponseBuilder {

    public static final String NEW_LINE = "\r\n";
    public static final String SPACE = " ";
    public static final String BODY_SPLITTER = NEW_LINE;

    public static final String HTTP_VERSION_1_1 = "HTTP/1.1";

    public static final int STATUS_CODE_200 = 200;
    public static final int STATUS_CODE_500 = 500;

    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_CONTENT_LENGTH = "Content_Length";
    public static final String MIME_TYPE_HTML = "text/html";

    private String httpVersion;
    private int statusCode;
    private Map<String, String> headerParam;

    private String body;

    public HttpResponseBuilder() {
        headerParam = new HashMap<>();
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setHeaderParam(Map<String, String> headerParam) {
        this.headerParam = headerParam;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaderParam() {
        return headerParam;
    }

    public String getBody() {
        return body;
    }

    public String build() {
        StringBuilder httpResponseBuilder = new StringBuilder();
        httpResponseBuilder.append(HTTP_VERSION_1_1)
                .append(SPACE)
                .append(statusCode)
                .append(NEW_LINE);
        for (Map.Entry<String, String> entry : headerParam.entrySet()) {
            httpResponseBuilder.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append(NEW_LINE);
        }

        if (body != null && !body.isEmpty()) {
            httpResponseBuilder
                    .append(BODY_SPLITTER);
            httpResponseBuilder.append(body);
        }

        return httpResponseBuilder.toString();
    }

}
