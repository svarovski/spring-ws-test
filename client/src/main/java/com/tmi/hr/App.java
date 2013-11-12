package com.tmi.hr;

import com.tmi.hr.schemas.CountRequest;
import com.tmi.hr.schemas.CountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("client-context.xml");
        HolidayService holidayService = context.getBean("holidayService", HolidayService.class);

        String string = "123 456 789";
        CountResponse countResponse = holidayService.handleCountChars(new CountRequest(string));
        log.info(String.format("'%s' => %d %d", string, countResponse.getWords(), countResponse.getChars()));
    }
}
