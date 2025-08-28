package com.eirlss.demo;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@RestController
@CrossOrigin("*")
public class ValidateLicenseController {

    @GetMapping("/CheckLicenseValidity/{licenseNo}")
    public String checkLicense(@PathVariable("licenseNo") String licenseNo)
    {
        String line = "";
        String csvSplitBy = ",";

        File csvFile = new File("C:\\Users\\Maasha\\Desktop\\DMVData.csv");
        if(csvFile.isFile())
        {
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))){
                int iteration = 0;
                while ((line = bufferedReader.readLine()) != null)
                {
                    if(iteration==0)
                    {
                        iteration++;
                        continue;
                    }
                    String[] licenseNumbers = line.split(csvSplitBy);
                    if(licenseNumbers[0].equals(licenseNo))
                    {
                        return "Suspended";
                    }else if(licenseNumbers[1].equals(licenseNo))
                    {
                        return "Lost";
                    }
                    else if(licenseNumbers[2].equals(licenseNo))
                    {
                        return "Stolen";
                    }
                }
                return "Valid";
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return "Invalid File";
    }
}
