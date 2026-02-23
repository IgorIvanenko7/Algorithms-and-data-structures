package ru.t1.education;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileGroupBy {

    static String PATH_FILE = "src/main/resources/SourceNames.txt";

    public static void main(String[] args) {
        var utillClass = new UtillClass();
        // Получить содержимое файла
        var listName = utillClass.getListFromFile(PATH_FILE);
        System.out.println("--------------------");
        var resultGroupBy = listName.stream()
                .parallel()
                .filter(name -> utillClass.notNumber.test(name))
                .map(name -> utillClass.formatName.apply(name))
                .collect(Collectors.groupingBy(
                        s -> s.charAt(s.length() - 1),
                        Collectors.mapping(
                                s -> s.replaceAll("[\\d:]", ""),
                                Collectors.toList()
                        )
                ));
        System.out.println(resultGroupBy);
    }
}

class UtillClass {
    /**
     *  Считываение содержимого файла в ArrayList
     */
    public List<String> getListFromFile(String path) {
        var listRec = new ArrayList<String>();
        String rec;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((rec = br.readLine()) != null) {
                listRec.add(rec);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return  listRec;
    }

    /**
     *  Приведение имени к нормальному виду: Первый символ в верхнем регистре, остальные в нижнем
     */
    Function<String, String> formatName = (inName) ->
            inName.substring(0, 1).toUpperCase() + inName.substring(1).toLowerCase();

    /**
     * Предикат на имена без номеров
     */
    Predicate<String> notNumber = (name) ->
            name.matches(".*\\d");
}