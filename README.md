# trta-idpt_extract-validator
Automated validation framework for validating the Content Extract output, vs the Content Extract Definition and Company Config data from Determination.

Endpoint where this tool hosted- http://cre-sdm2-alb-1249178167.us-east-1.elb.amazonaws.com/test/run/extractvalidation.

Request Body- 

{
    "cleanupModelScenario": true,
    "contentExtractBaseUrl": "<< CE service URL for SAT/QA/Dev >>",
   "determinationBaseUrl": "<< Model Scenario URL for SAT/QA/Dev>>",
    "lineGrossAmounts": [
        "<< comma separated list for multiple gross amounts >>"
    ],
    "modelScenarioName": "<< Model Scenario name >>",
    "servicePassword": "<<  password for CE service - who have authorization to fetch the extract data >>"
    "serviceUser": "<< username for CE service -  who have authorization to fetch the extract data >>",
    "testCompanyName": "<< extract company name >>",
    "testExtractConfigName": "<< extract name >>",
    "testRunNumber": "1",
    "envCredentialsID": << Your username for SAT/QA/Dev Environment >>,
    "envCredentialsPassword": "<< Your password for SAT/QA/Dev Environment >>",
    "environmentMS": "<< Model Scneario environment >>",
    "skipScenarios": << No of scenarios you want to skip after starting this tool for an extract >>,
    "testCompanyUUID": "<< extract company UUID >>",
    "testCompanyID": " << extract company ID >>",
    "taxType": "<< tax  type like SA, US >>"
       
}
