// "xmlns" namespace remapping allows to remove "ns2" namespace prefix from response.
// but it is not guaranteed to work with java < 7

@XmlSchema(namespace = HolidayEndpoint.NAMESPACE_URI,
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(namespaceURI = HolidayEndpoint.NAMESPACE_URI, prefix = "")
        }
)
package com.tmi.hr.schemas;

import com.tmi.hr.HolidayEndpoint;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
