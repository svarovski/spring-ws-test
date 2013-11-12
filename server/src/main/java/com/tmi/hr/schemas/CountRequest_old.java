package com.tmi.hr.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CountRequest", namespace = "http://com.tmi.hr/hr/schemas")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountRequest_old {
    private String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "CountRequest{target='" + target + "'}";
    }
}
