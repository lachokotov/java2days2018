package com.java2days.example.slip;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class SlipTest extends CamelBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/blueprint.xml";
	}

	@Test
	public void testRoutingSlip() throws Exception {
		// setup expectations that only A and C will receive the message
		getMockEndpoint("mock:a").expectedMessageCount(1);
		getMockEndpoint("mock:b").expectedMessageCount(0);
		getMockEndpoint("mock:c").expectedMessageCount(1);

		// send the incoming message
		template.sendBody("direct:start", "Hello World");

		assertMockEndpointsSatisfied();
	}

	@Test
	public void testRoutingSlipCool() throws Exception {
		// setup expectations that all will receive the message
		getMockEndpoint("mock:a").expectedMessageCount(1);
		getMockEndpoint("mock:b").expectedMessageCount(1);
		getMockEndpoint("mock:c").expectedMessageCount(1);

		template.sendBody("direct:start", "We are Cool");

		assertMockEndpointsSatisfied();
	}

}
