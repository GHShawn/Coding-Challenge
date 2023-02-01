package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportingEmployeeIdUrl;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingEmployeeIdUrl = "http://localhost:" + port + "/reporting/{id}";
    }

    //Test total number of reports for an employee by calling reportingEmployeeIdUrl API
    //Three test data was given
    //Should expected to return 4, 2 and 0 number of reports for given employeeID.
    @Test
    public void testTotalNumberOfReports() {
        String employeeIDWithFourReports = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        ReportingStructure report = restTemplate.getForEntity(reportingEmployeeIdUrl, ReportingStructure.class, employeeIDWithFourReports).getBody();
        assertEquals(4, report.getNumberOfReports());

        String employeeIDWithTwoReports = "03aa1462-ffa9-4978-901b-7c001562cf6f";
        ReportingStructure report2 = restTemplate.getForEntity(reportingEmployeeIdUrl, ReportingStructure.class, employeeIDWithTwoReports).getBody();
        assertEquals(2, report2.getNumberOfReports());

        String employeeIDWithZeroReports = "c0c2293d-16bd-4603-8e08-638a9d18b22c";
        ReportingStructure report3 = restTemplate.getForEntity(reportingEmployeeIdUrl, ReportingStructure.class, employeeIDWithZeroReports).getBody();
        assertEquals(0, report3.getNumberOfReports());
    }

   
}
