package com.stocks.services;

import com.stocks.dao.CompanyRepository;
import com.stocks.domain.Attribute;
import com.stocks.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyStockServiceImpl implements CompanyStockService {
    @Qualifier("attributeRepository")
    @Autowired
    CompanyRepository attributeRepository;

    @Qualifier("stockRepository")
    @Autowired
    CompanyRepository stockRepository;

    @Transactional(readOnly = true)
    @Override
    public String findName(String symbol) {
        List<Attribute> attributeList = attributeRepository.findAttribute(symbol);
        return attributeList.get(0).getCompanyName();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Stock> findPriceList(String symbol) {

        return stockRepository.findPriceList(symbol);
    }

    @Transactional(readOnly = true)
    @Override
    public ArrayList<Map<String, Integer>> getBoundaryDates(String symbol, List<Stock> stockList) {
        ArrayList<Map<String, Integer>> dateList = new ArrayList<Map<String, Integer>>();
        int idx = 0;
        while(idx < stockList.size()) {
            Map<String, Integer> dateMap = new HashMap<String, Integer>();
            dateMap.put("year", stockList.get(idx).getYear());
            dateMap.put("month", stockList.get(idx).getMonth());
            dateMap.put("day", stockList.get(idx).getDay());
            dateList.add(dateMap);
            idx += stockList.size() - 1;
        }
        return dateList;
    }

    @Transactional
    @Override
    public String writeJSONData(List<Stock> stockList, String title) throws FileNotFoundException {
        JsonObjectBuilder postBuilder = Json.createObjectBuilder();
        JsonArrayBuilder dataBuilder = Json.createArrayBuilder();
        JsonArrayBuilder stockBuilder = Json.createArrayBuilder();

        for (Stock stock : stockList) {
            // subtract 1 from month to represent Javascript's Jan = 0
            int month = stock.getMonth() - 1;
            stockBuilder.add(stock.getYear());
            stockBuilder.add(month);
            stockBuilder.add(stock.getDay());

            DecimalFormat df=new DecimalFormat("#.##");

            // convert float to 2 decimal places using DecimalFormat then parse to a double
            stockBuilder.add(Double.parseDouble(df.format(stock.getOpen()))); // open round 2 decimals
            stockBuilder.add(Double.parseDouble(df.format(stock.getHigh()))); // high round 2 decimals
            stockBuilder.add(Double.parseDouble(df.format(stock.getLow()))); // low round 2 decimals
            stockBuilder.add(Double.parseDouble(df.format(stock.getClose()))); // close round 2 decimals
            stockBuilder.add(stock.getVolume()); // volume already int
            dataBuilder.add(stockBuilder);
        }
        postBuilder.add("data", dataBuilder);
        JsonObject postJsonObject = postBuilder.build();
        System.out.println("Post JSON String -> " + postJsonObject);

        // write to file in spring boot's resources
        String fileName = title + ".json";
        String location = "/Users/vanessashultz/Documents/DATABASE_REVAMPED/Project/StockApp/StockApp/src/main/resources/static/" + fileName;
        OutputStream os = new FileOutputStream(location);
        JsonWriter jsonWriter = Json.createWriter(os);

        Map<String, Boolean> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonWriterFactory factory = Json.createWriterFactory(config);
        jsonWriter = factory.createWriter(os);

        jsonWriter.writeObject(postJsonObject);
        jsonWriter.close();
        return fileName;
    }
}