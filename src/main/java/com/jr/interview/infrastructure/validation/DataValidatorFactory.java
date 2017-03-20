package com.jr.interview.infrastructure.validation;

import com.jr.interview.common.model.Test;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by s.georgakis on 3/3/2017.
 */
public class DataValidatorFactory {

    private Map<Class, DataValidator> dataValidatorMap;
    private static DataValidatorFactory dataValidatorFactory;

    private static final Logger LOG = LoggerFactory.getLogger(DataValidatorFactory.class);

    public static DataValidatorFactory getInstance() {
        if (dataValidatorFactory == null) {
            dataValidatorFactory = new DataValidatorFactory();
            LOG.debug("DataValidatorFactory created");
        }
        return dataValidatorFactory;
    }

    private DataValidatorFactory() {
        dataValidatorMap = new HashedMap();
    }

    public DataValidator getDataValidator(Class modelClass) {
        if (modelClass.equals(Test.class)) {
            if (!dataValidatorMap.containsKey(Test.class)) {
                dataValidatorMap.put(Test.class, new DataValidator("xmlValidation/validation.xsd"));
                LOG.debug("Added validator for class {} in map.", modelClass);
            }
        } else {
            LOG.error("Class for validation not recognized. {}", modelClass);
        }
        return dataValidatorMap.get(Test.class);
    }
}
