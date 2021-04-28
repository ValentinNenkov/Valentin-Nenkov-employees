package main;

import main.exceptions.NotFoundException;
import main.exceptions.UnknownFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileParser {
    private final static String DATE_FORMAT1 = "^\\d{8}$";
    private final static String DATE_FORMAT2 = "^\\d{1,2}-\\d{1,2}-\\d{4}$";
    private final static String DATE_FORMAT3 = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
    private final static String DATE_FORMAT4 = "^\\d{1,2}/\\d{1,2}/\\d{4}$";
    private final static String DATE_FORMAT5 = "^\\d{4}/\\d{1,2}/\\d{1,2}$";
    private final static String DATE_FORMAT6 = "^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$";
    private final static String DATE_FORMAT7 = "^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$";

    public static ArrayList<Person> readFromFile() throws NotFoundException, UnknownFormatException {
        File newFile = new File("text.txt");

        try (Scanner sc = new Scanner(newFile)) {
            ArrayList<Person> persons = new ArrayList<>();
            while (sc.hasNextLine()) {
                java.lang.String line = sc.nextLine();
                java.lang.String[] lineWords = line.split("[, ]+");
                Person p = new Person(lineWords[0]);
                String projectId = lineWords[1];

                p.addProject(projectId, dateFormat(lineWords[2]), dateFormat(lineWords[3]));
                persons.add(p);
            }
            return persons;
        } catch (FileNotFoundException e) {
            throw new NotFoundException("File not found");
        }
    }

    public static LocalDateTime dateFormat(String date) throws UnknownFormatException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (date.toLowerCase().equals("null")) {
            LocalDateTime dateTime = LocalDateTime.now();
            return dateTime;
        }
        if (date.matches(DATE_FORMAT3)) {
            LocalDate parsedDate = LocalDate.parse(date);
            return parsedDate.atStartOfDay();
        }
        if (date.matches(DATE_FORMAT1)) {
            LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
            String formatedDateString = dateTime.format(format);
            LocalDate parsedDate = LocalDate.parse(formatedDateString);
            return parsedDate.atStartOfDay();
        }
        if (date.matches(DATE_FORMAT2)) {
            LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String formatedDateString = dateTime.format(format);
            LocalDate parsedDate = LocalDate.parse(formatedDateString);
            return parsedDate.atStartOfDay();
        }
        if (date.matches(DATE_FORMAT4)) {
            LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            String formatedDateString = dateTime.format(format);
            LocalDate parsedDate = LocalDate.parse(formatedDateString);
            return parsedDate.atStartOfDay();
        }
        if (date.matches(DATE_FORMAT5)) {
            LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String formatedDateString = dateTime.format(format);
            LocalDate parsedDate = LocalDate.parse(formatedDateString);
            return parsedDate.atStartOfDay();
        }
        if (date.matches(DATE_FORMAT6)) {
            LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy"));
            String formatedDateString = dateTime.format(format);
            LocalDate parsedDate = LocalDate.parse(formatedDateString);
            return parsedDate.atStartOfDay();
        }
        if (date.matches(DATE_FORMAT7)) {
            LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMMM yyyy"));
            String formatedDateString = dateTime.format(format);
            LocalDate parsedDate = LocalDate.parse(formatedDateString);
            return parsedDate.atStartOfDay();
        }
        throw new UnknownFormatException("Format unknown");
    }

}
