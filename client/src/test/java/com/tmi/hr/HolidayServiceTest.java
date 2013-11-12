package com.tmi.hr;

import javax.xml.transform.Source;

import com.tmi.hr.schemas.CountRequest;
import com.tmi.hr.schemas.CountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import org.springframework.ws.test.client.MockWebServiceServer;
import static org.springframework.ws.test.client.RequestMatchers.*;
import static org.springframework.ws.test.client.ResponseCreators.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:client-context.xml")
public class HolidayServiceTest {
    @Autowired
    private HolidayService holidayService;

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    private MockWebServiceServer mockServer;

    @Before
    public void createServer() throws Exception {
        mockServer = MockWebServiceServer.createServer(webServiceTemplate);
    }

    @Test
    public void testHandleCountChars() throws Exception {
        String string = "John Doe";
        Source requestPayload = new StringSource(
            "<CountRequest xmlns='" + HolidayEndpoint.NAMESPACE_URI + "'>" +
                "<Target>" + string + "</Target>" +
                "</CountRequest>");
        Source responsePayload = new StringSource(
            "<CountResponse xmlns='" + HolidayEndpoint.NAMESPACE_URI + "'>" +
                "<Words>33</Words>" +
                "<Chars>44</Chars>" +
                "</CountResponse>");

        mockServer.expect(payload(requestPayload)).andRespond(withPayload(responsePayload));

        CountResponse result = holidayService.handleCountChars(new CountRequest(string));
        assertEquals(33, result.getWords().intValue());
        assertEquals(44, result.getChars().intValue());

        mockServer.verify();
    }
}
