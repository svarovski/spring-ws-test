package com.tmi.hr;

import com.tmi.hr.schemas.CountRequest;
import com.tmi.hr.schemas.CountResponse;
import com.tmi.hr.service.HumanResourceService;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.text.SimpleDateFormat;
import java.util.Date;

@Endpoint
public class HolidayEndpointImpl implements HolidayEndpoint {
    private static final Logger log = LoggerFactory.getLogger(HolidayEndpointImpl.class);

    private static final Namespace namespace = Namespace.getNamespace("hr", NAMESPACE_URI);

    private XPathExpression<Element> startDateExpression;
    private XPathExpression<Element> endDateExpression;
    private XPathExpression<Element> nameExpression;
    private XPathExpression<Element> surnameExpression;

    private HumanResourceService humanResourceService;

    @Autowired
    public HolidayEndpointImpl(HumanResourceService humanResourceService) throws JDOMException {
        this.humanResourceService = humanResourceService;

        XPathFactory xpathFactory = XPathFactory.instance();
        startDateExpression = xpathFactory.compile("//hr:StartDate", Filters.element(), null, namespace);
        endDateExpression = xpathFactory.compile("//hr:EndDate", Filters.element(), null, namespace);
        nameExpression = xpathFactory.compile("//hr:FirstName", Filters.element(), null, namespace);
        surnameExpression = xpathFactory.compile("//hr:LastName", Filters.element(), null, namespace);
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = HOLIDAY_REQUEST_LOCALPART)
    public void handleHolidayRequest(@RequestPayload Element holidayRequest) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(startDateExpression.evaluate(holidayRequest).get(0).getValue());
        Date endDate = sdf.parse(endDateExpression.evaluate(holidayRequest).get(0).getValue());
        String name = nameExpression.evaluate(holidayRequest).get(0).getValue() + " " + surnameExpression.evaluate(holidayRequest).get(0).getValue();

        humanResourceService.bookHoliday(startDate, endDate, name);
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = COUNT_REQUEST_LOCALPART)
    @ResponsePayload
    public CountResponse handleCountChars(@RequestPayload CountRequest countRequest) throws Exception {
        String target = countRequest.getTarget();

        log.info("got target '{}'", target);
        return new CountResponse(target.split("\\s+").length, target.length());
    }

}
