package com.tmi.hr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:hr-servlet.xml")
public class HolidayEndpointImplTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void testCountChars() throws Exception {
        Source requestPayload = new StringSource(
            "<CountRequest xmlns='" + HolidayEndpoint.NAMESPACE_URI + "'>" +
                "<Target>John Doe</Target>" +
            "</CountRequest>");
        Source responsePayload = new StringSource(
            "<CountResponse xmlns='" + HolidayEndpoint.NAMESPACE_URI + "'>" +
                "<Words>2</Words>" +
                "<Chars>8</Chars>" +
            "</CountResponse>");

        mockClient.sendRequest(
                withPayload(requestPayload)).
                andExpect(payload(responsePayload));
    }
}
