package com.java2days.example.dynamicrouter;

import org.apache.camel.Exchange;
import org.apache.camel.Header;

/**
 * Bean which implements the logic where the message should be routed by the Dynamic Router EIP.
 */
public class DynamicRouterBean {

    /**
     * The method invoked by Dynamic Router EIP to compute where to go next.
     *
     * @param body          the message body
     * @param previous   the previous endpoint, is <tt>null</tt> on the first invocation
     * @return endpoint uri where to go, or <tt>null</tt> to indicate no more
     */
    public String route(String body, @Header(Exchange.SLIP_ENDPOINT) String previous) {
        return whereToGo(body, previous);
    }

    /**
     * Method which computes where to go next
     */
    private String whereToGo(String body, String previous) {
        if (previous == null) {
            // 1st time
            return "mock://a";
        } else if ("mock://a".equals(previous)) {
            // 2nd time - transform the message body using the simple language
            return "language://simple:Bye ${body}";
        } else {
            // no more, so return null to indicate end of dynamic router
            return null;
        }
    }

}
