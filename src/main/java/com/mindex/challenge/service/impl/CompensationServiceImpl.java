package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    //Create compensation
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating employee compensation[{}]", compensation);
        //Set random compensation employee ID
        compensation.setEmployeeId(UUID.randomUUID().toString());
        compensationRepository.insert(compensation);

        return compensation;
    }

    /*
        Get employee compensation by employee id
        if compensation is null, throw exception
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Creating employee compensation with id [{}]", id);

        Compensation compensation = compensationRepository.findByEmployeeId(id);

        if (compensation == null) {
            throw new RuntimeException("Invalid compensationId: " + id);
        }

        return compensation;
    }


}
