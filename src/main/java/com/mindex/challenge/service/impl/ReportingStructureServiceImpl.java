package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);


    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure readReportingStructure(String id) {
        LOG.debug("Read employee's reporting structure[{}]", id);

        //Get targeted employee info
        Employee employee = employeeRepository.findByEmployeeId(id);

        //Check if employee info is not null
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        //Initialize reporting structure object
        ReportingStructure reporting = new ReportingStructure();

        //Set total number of reports that generated from the totalNumberOfReports method.
        reporting.setNumberOfReports(totalNumberOfReports(employee));

        //Set latest employee info for employee's reporting structure
        reporting.setEmployee(employee);
        return reporting;
    }

    //Return total number of reports
    private int totalNumberOfReports(Employee employee){

        //Initialize totalNumberOfReports to 0;
        int totalNumberOfReports = 0;

        //Check if employee has direct reports
        if(employee.getDirectReports() != null){
            List<Employee> reporting = new ArrayList<>();

            //Loop through employee's direct report list
            for(Employee directReport : employee.getDirectReports()){

                //Add employee info data to the directReport field
                Employee employeeInfo = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
                reporting.add(employeeInfo);
                employee.setDirectReports(reporting);

                //Increment totalNumberOfReports by 1 each time we loop
                totalNumberOfReports +=1;

                //Check if the current direct report employee has any directReport
                //by calling the method itself
                //recursive way
                totalNumberOfReports += totalNumberOfReports(employeeInfo);
            }
        }
        return totalNumberOfReports;
    }
}
