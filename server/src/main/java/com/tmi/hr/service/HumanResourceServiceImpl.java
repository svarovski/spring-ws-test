package com.tmi.hr.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HumanResourceServiceImpl implements HumanResourceService {
    @Override
    public void bookHoliday(Date startDate, Date endDate, String name) {
        System.out.println("Booking holiday for [" + startDate + "-" + endDate + "] for [" + name + "] ");
    }
}
