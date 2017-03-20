package com.jr.interview.infrastructure.validation;

import com.jr.interview.infrastructure.rest.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by s.georgakis on 1/3/2017.
 */
public class DataValidator {

    private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    private Validator validator;

    public DataValidator(String xsdPath) {
        ClassLoader classLoader = getClass().getClassLoader();
        File schemaFile = new File(classLoader.getResource(xsdPath).getFile());
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            validator = schema.newValidator();
            LOG.debug("Validator for schema {} successfully created", xsdPath);
        } catch (SAXException e) {
            LOG.error("An error occured while creating validator. Check xsd file: {}", e.getMessage());
            LOG.error(e.getStackTrace().toString());
        }
    }

    public boolean validateXml(Source xmlFile) {
        try {
            validator.validate(xmlFile);
            LOG.debug("XML File is valid.");
            return true;
        } catch (SAXException e) {
            LOG.error("XML File is NOT valid Reason: {}", e.getMessage());
            return false;
        } catch (IOException e) {
            LOG.error("Error with XML File. Reason: {}", e.getMessage());
            return false;
        }
    }
}
