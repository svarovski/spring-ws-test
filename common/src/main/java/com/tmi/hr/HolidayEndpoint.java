package com.tmi.hr;

import com.tmi.hr.schemas.CountRequest;
import com.tmi.hr.schemas.CountResponse;
import org.jdom2.Element;

public interface HolidayEndpoint {
    public static final String NAMESPACE_URI = "http://com.tmi.hr/hr/com.tmi.hr.schemas";

    public static final String HOLIDAY_REQUEST_LOCALPART = "HolidayRequest";
    public static final String COUNT_REQUEST_LOCALPART = "CountRequest";

    public void handleHolidayRequest(Element holidayRequest) throws Exception;

    public CountResponse handleCountChars(CountRequest countRequest) throws Exception;
}
