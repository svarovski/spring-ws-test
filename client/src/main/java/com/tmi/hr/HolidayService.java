package com.tmi.hr;

import com.tmi.hr.schemas.CountRequest;
import com.tmi.hr.schemas.CountResponse;
import org.jdom2.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class HolidayService implements HolidayEndpoint {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Override
    public void handleHolidayRequest(Element holidayRequest) throws Exception {
    }

    @Override
    public CountResponse handleCountChars(CountRequest countRequest) throws Exception {
        return (CountResponse)webServiceTemplate.marshalSendAndReceive(countRequest);
    }
}
