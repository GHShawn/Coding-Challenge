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

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reporting = new ReportingStructure();
        reporting.setEmployee(employee);
        reporting.setNumberOfReports(totalNumberOfReports(employee));
        return reporting;
    }

    private int totalNumberOfReports(Employee employee){
        int totalNumberOfReports = 0;
        if(employee.getDirectReports() != null){
            List<Employee> reporting = new ArrayList<>();

            for(Employee directReport : employee.getDirectReports()){
                Employee employeeInfo = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
                reporting.add(employeeInfo);
                employee.setDirectReports(reporting);
                totalNumberOfReports +=1;
                totalNumberOfReports += totalNumberOfReports(employeeInfo);
            }
        }
        return totalNumberOfReports;
    }
}
