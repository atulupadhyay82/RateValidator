package com.thomsonreuters.extractvalidator.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thomsonreuters.extractvalidator.dto.RunResults;
import com.thomsonreuters.extractvalidator.dto.TestCase;
import com.thomsonreuters.extractvalidator.dto.TestRun;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestExtractValidator {

    @Autowired
    private RestTemplate testRestTemplate;

    private TestRun requestBean;

    private String requestBody;

    @LocalServerPort
    private int randomServerPort;

    private String baseURL;

    @PostConstruct
    public void init() {
        baseURL = "http://localhost:" + randomServerPort;
    }



    @Before
    public void setUp(){
        TestRun requestBean= new TestRun();

        requestBean.setCleanupModelScenario(true);
       //requestBean.setContentExtractBaseUrl("http://cre-sdm2-alb-1249178167.us-east-1.elb.amazonaws.com/");
        //requestBean.setContentExtractBaseUrl("http://localhost:8101");
         requestBean.setContentExtractBaseUrl("https://cre-api-qa.onesourcetax.com/");
        requestBean.setDeterminationBaseUrl("https://det-cre-sat.onesourcetax.com/");
        List<String> lineGrossAmount =new ArrayList<String>();

        lineGrossAmount.add("1000");

//        lineGrossAmount.add("36000");
        requestBean.setLineGrossAmounts(lineGrossAmount);
        requestBean.setModelScenarioName("Tier_Model");
       requestBean.setServicePassword("e95XnPgNsDVxpPQP");
        //requestBean.setServicePassword("password");
        requestBean.setServiceUser("^elvis-rest-client");
        requestBean.setSkipScenarios(0);
//        requestBean.setTestCompanyName("VTest Industries");
//        requestBean.setTestExtractConfigName("VTestVE-TaxType");
//        requestBean.setTestCompanyID("7760");
//        requestBean.setTestCompanyUUID("1fb52dcb-d2aa-4857-987d-5da979948a59");
        requestBean.setTestExtractConfigName("WayfairUAT_45_UT");
        requestBean.setTestCompanyName("01_Wayfair_US");
        requestBean.setTestCompanyID("18145");
        requestBean.setTestCompanyUUID("67e8a3b4-f2f1-4286-90c4-611c5dbce973");
       // requestBean.setProductCategoryName("Installation Service Charges - Separately Negotiated");
        requestBean.setTestRunNumber("1");
        requestBean.setTaxType("SA");
        requestBean.setEnvCredentialsID("Atul.Upadhyay.vbr");
        requestBean.setEnvCredentialsPassword("Dec@1234");
        requestBean.setEnvironmentMS("SAT");

       // requestBean.setTestCompanyID("249");

        Set<String> jurList=new TreeSet<>();
       File f1=new File("C:\\dell\\functional\\622\\diff.txt"); //Creation of File Descriptor for input file
        String[] words=null;  //Intialize the word Array
        FileReader fr = null;  //Creation of File Reader object
        try {
            fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
            String line;


            while((line=br.readLine())!=null){
                words=line.split(":");
                jurList.add(words[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(jurList);
       List<String> jKey=new ArrayList<String>(jurList);
//      jKey.add("-2647363409393057279");
//      jKey.add("-7793131385176793444");
//        jKey.add("7219298813336683952");
//        jKey.add("9215050770970968955");
//        jKey.add("3886350493483257854");
//        jKey.add("7375307450378410534");
//        jKey.add("4728128362363438803");
//        jKey.add("7375307450378410534");

    requestBean.setJurisdictionKeys(jKey);

        List<String> postalList=new ArrayList<String>();
        postalList.add("63069");
        postalList.add("63119");
        postalList.add("63127");
        postalList.add("63144");
        postalList.add("64012");
        postalList.add("64081");
        postalList.add("65588");

        //requestBean.setPostalCodeList(postalList);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        requestBody = gson.toJson(requestBean);


    }



    @Test
    public void generateResultFromService() throws Exception{
        baseURL += "/test/run/extractvalidation";

        URI uri = new URI(baseURL);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Content-Type","charset=UTF-8");

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<RunResults> result = this.testRestTemplate.exchange(uri, HttpMethod.POST,request,RunResults.class);
        printResult(result.getBody());

    }

    private void printResult(RunResults results) throws IOException {
       List<TestCase> testCases= results.getTestCases();
       List<TestCase> failedCases= new ArrayList<>();
       int counter=0;
       int size=testCases.size();
       for(TestCase testCase: testCases){
           if(testCase.getTestResult().contains("FAILED")){
               failedCases.add(testCase);
               counter++;
           }
       }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println("total passed result: "+ (size-counter)+ " and total failed cases: "+ counter);
        File file=new File(System.getProperty("user.dir")+"\\OutputResults.json");
        String writeResultData= gson.toJson(failedCases);

        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(System.getProperty("user.dir")+"\\OutputResults.json"));
            os.write(writeResultData.getBytes(), 0, writeResultData.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("File created at : "+System.getProperty("user.dir")+"\\OutputResults.json");
    }
}
