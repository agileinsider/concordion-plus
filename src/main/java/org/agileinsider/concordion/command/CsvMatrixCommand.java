package org.agileinsider.concordion.command;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import com.googlecode.jcsv.reader.internal.DefaultCSVEntryParser;
import org.concordion.api.CommandCall;
import org.concordion.api.Element;
import org.concordion.api.Evaluator;
import org.concordion.api.ResultRecorder;
import org.concordion.internal.command.AssertEqualsCommand;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class CsvMatrixCommand extends AssertEqualsCommand {
    @Override
    public void verify(CommandCall commandCall, Evaluator evaluator, ResultRecorder resultRecorder) {
        try {
            URL csvUrl = getCsvUrl(commandCall, evaluator);
            CSVReader<String[]> csvReader = createCsvReader(csvUrl);
            String[] headers = csvReader.readNext();

            Element htmlResultsTable = new Element("table");
            addHeadersTo(htmlResultsTable, headers);
            commandCall.getElement().appendSister(htmlResultsTable);

            String[] row;
            while ((row = csvReader.readNext()) != null) {
                String rowFirstColumnValue = row[0];
                evaluator.setVariable("#row", rowFirstColumnValue);

                Element htmlResultsRow = new Element("tr");
                htmlResultsRow.appendChild(new Element("td").appendText(rowFirstColumnValue));
                htmlResultsTable.appendChild(htmlResultsRow);

                for (int i = 1; i < row.length; i++) {
                    String columnHeaderValue = headers[i];
                    String cellValue = row[i];

                    evaluator.setVariable("#column", columnHeaderValue);
                    evaluator.setVariable("#cell", cellValue);

                    Element cellElement = new Element("td").appendText(cellValue);
                    htmlResultsRow.appendChild(cellElement);

                    commandCall.setElement(cellElement);
                    super.verify(commandCall, evaluator, resultRecorder);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL getCsvUrl(CommandCall commandCall, Evaluator evaluator) throws MalformedURLException {
        String href = commandCall.getElement().getAttributeValue("href");
        URL resource = ExposeFixtureClassHack.getFixtureClass(evaluator).getResource(href);
        if (resource == null) {
            resource = new URL(href);
        }
        return resource;
    }

    private void addHeadersTo(Element resultTable, String[] headers) {
        Element header = new Element("thead");
        resultTable.appendChild(header);

        Element row = new Element("tr");
        header.appendChild(row);

        for (String headerText : headers) {
            Element headerCell = new Element("th");
            headerCell.appendText(headerText);
            row.appendChild(headerCell);
        }
    }

    private CSVReader<String[]> createCsvReader(URL url) throws IOException {
        Reader reader = new InputStreamReader(url.openConnection().getInputStream());
        DefaultCSVEntryParser csvEntryParser = new DefaultCSVEntryParser();
        CSVStrategy csvStrategy = CSVStrategy.UK_DEFAULT;
        return new CSVReaderBuilder<String[]>(reader).entryParser(csvEntryParser).strategy(csvStrategy).build();
    }
}
