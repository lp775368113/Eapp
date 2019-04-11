
package com.servicecode;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.servicecode package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetQueryData_QNAME = new QName("http://www.WebService.demo.example.com", "getQueryData");
    private final static QName _GetQueryDataResponse_QNAME = new QName("http://www.WebService.demo.example.com", "getQueryDataResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.servicecode
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetQueryData }
     * 
     */
    public GetQueryData createGetQueryData() {
        return new GetQueryData();
    }

    /**
     * Create an instance of {@link GetQueryDataResponse }
     * 
     */
    public GetQueryDataResponse createGetQueryDataResponse() {
        return new GetQueryDataResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQueryData }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetQueryData }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.WebService.demo.example.com", name = "getQueryData")
    public JAXBElement<GetQueryData> createGetQueryData(GetQueryData value) {
        return new JAXBElement<GetQueryData>(_GetQueryData_QNAME, GetQueryData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQueryDataResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetQueryDataResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.WebService.demo.example.com", name = "getQueryDataResponse")
    public JAXBElement<GetQueryDataResponse> createGetQueryDataResponse(GetQueryDataResponse value) {
        return new JAXBElement<GetQueryDataResponse>(_GetQueryDataResponse_QNAME, GetQueryDataResponse.class, null, value);
    }

}
