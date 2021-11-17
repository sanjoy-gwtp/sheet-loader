package com.surjo.sheetloader;

import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sanjoy
 * on 11/16/21
 */
@RestController
@RequestMapping("read")
public class ReadController {

    private final SheetsService sheetsService;

    public ReadController(SheetsService sheetsService) {
        this.sheetsService = sheetsService;
    }

    @RequestMapping(path = "/data/{sheetId}", method = RequestMethod.GET)
    public ResponseEntity getData(@PathVariable("sheetId") String sheetId){
        List<MonthlyExpense> response = new ArrayList<>();
        try {
            ValueRange readResult = sheetsService.getSheets().spreadsheets()
                    .values().get(sheetId,"january!A1:C").execute();

            List<List<Object>> values = readResult.getValues();
            if (values == null || values.isEmpty()) {
                System.out.println("No data found.");
            } else {
                System.out.println("Count, Description,Cost");
                for (List row : values) {
                    System.out.printf("%s, %s, %s\n", row.get(0),row.get(1),row.get(2));
                    MonthlyExpense expense=new MonthlyExpense(Long.valueOf(row.get(0).toString()),row.get(1).toString(),new BigDecimal(row.get(2).toString()));
                    response.add(expense);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

}
