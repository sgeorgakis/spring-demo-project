package com.jr.interview.infrastructure.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jr.interview.common.model.ObjectFactory;
import com.jr.interview.common.model.Test;
import com.jr.interview.infrastructure.validation.DataValidatorFactory;
import com.jr.interview.persistence.repositories.Test2Repository;
import com.jr.interview.persistence.repositories.TestRepository;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by s.georgakis on 1/3/2017.
 */

@RestController
public class Controller {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Controller.class);

    private Test2Repository test2Repository;
    private TestRepository testRepository;
    private ObjectMapper mapper;
    private DataValidatorFactory dataValidatorFactory;

    public Controller() {
        this.mapper = new ObjectMapper();
        this.dataValidatorFactory = DataValidatorFactory.getInstance();
    }

    @Autowired
    public void setTest2Repository(Test2Repository test2Repository) {
        this.test2Repository = test2Repository;
    }

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }


    @RequestMapping("fetchTest2/{name}")
    String fetchTest2(@PathVariable Integer id) {
        return test2Repository.findOne(id);
    }

    @RequestMapping("fetchTest/{name}")
    String fetchTest(@PathVariable Integer id) {
        return testRepository.findOne(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    String adderTest(@RequestBody StreamSource body) {
        // Angular needs a json response
        JSONObject response = new JSONObject();
        try {
            LOG.info("Incoming post test request");

            // We need to read the stream twice
            // to validate and to unmarshall
            InputStream inputStream = body.getInputStream();
            Source schemaSource = new StreamSource(inputStream);
            if (dataValidatorFactory.getDataValidator(Test.class).validateXml(schemaSource)) {
                inputStream.reset();
                JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
                Unmarshaller u = jc.createUnmarshaller();
                Test test = (Test) u.unmarshal(body);
                LOG.debug("Test {}", test.toString());
                testRepository.save(test);
                LOG.info("Saved " + test.toString());
                response.append("message", "Saved " + test.getName());
            } else {
                LOG.error("XML is NOT valid");
                response.append("message", "XML is NOT valid");
            }
        } catch (IOException|JAXBException e) {
            LOG.error("Exception occured: {}", e.getMessage());
            response.append("message", "Error: " + e.getMessage());
        }
        return response.toString();
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    String adderTest2(@RequestBody StreamSource body) {
//        try {
//            LOG.info("Incoming post test 2 request");
//            // We need to read the stream twice
//            // to validate and to unmarshall
//            InputStream inputStream = body.getInputStream();
//            Source schemaSource = new StreamSource(inputStream);
//            if (TestDataValidator.validateXml(schemaSource)) {
//                inputStream.reset();
//                JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
//                Unmarshaller u = jc.createUnmarshaller();
//                Test2 test2 = (Test2) u.unmarshal(body);
//                test2Repository.save(test2);
//                return "Saved " + test2.toString() + "!\n";
//            } else {
//                return "XML is NOT valid";
//            }
//        } catch (IOException|JAXBException e) {
//            LOG.error("Exception occured: {}", e.getMessage());
//            return "Error: " + e.getMessage();
//        }
        return null;
    }

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    String fetchAll() {
        List<Test> testList = testRepository.findAll();
        String response = null;
        try {
            response = mapper.writeValueAsString(testList);
        } catch (JsonProcessingException e) {
            response = e.getMessage();
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    String removeTest(@PathVariable Integer id) {
        JSONObject response = new JSONObject();
        LOG.info("Incoming delete request");
        Test test = testRepository.findOne(id);
        if (test != null) {
            testRepository.delete(test);
            LOG.debug("Test {} is successfully deleted", test.getId());
            response.append("message", test.getName() + " is successfully deleted!");
        } else {
            LOG.debug("Test with id {} was not found", id);
            response.append("message", String.format("Test with id %s was not found!", id.toString()));
        }
        return response.toString();
    }
}
