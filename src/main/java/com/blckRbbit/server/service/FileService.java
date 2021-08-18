package com.blckRbbit.server.service;

import com.blckRbbit.server.entity.FileEntity;
import com.blckRbbit.server.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void register(FileEntity files) {
        String path = files.getDirectory().getPath();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles==null || listOfFiles.length == 0) {
            jdbcTemplate.update("insert into file_entity(name, size, directory_and_files) values (?, ?, ?)",
                    "empty", 0, files.getDirectory().getId());
        }

        assert listOfFiles != null;
        List<File> sorted = sort(listOfFiles);

        for(File file : sorted) {
            jdbcTemplate.update("insert into file_entity(name, size, directory_and_files) values (?, ?, ?)",
                    file.getName(), file.length(), files.getDirectory().getId());
        }
    }

    private List<File> sort(File[] listOfFiles) {
        // вывод списка по очереди директории -> файлы
        List<File> dirs= new ArrayList<>();
        List<File> files= new ArrayList<>();
        for(File file : listOfFiles) {
            if (file.isDirectory()) {
                dirs.add(file);
            }
            if (file.isFile()) {
                files.add(file);
            }
        }

        Collections.sort(dirs);
        Collections.sort(files);

        sortOfNumber(dirs);
        sortOfNumber(files);

        if (dirs.isEmpty()) {
            return files;
        } else if (files.isEmpty()) {
            return dirs;
        }
        List<File> result = new ArrayList<>(dirs);
        result.addAll(files);
        return result;
    }

    private List<File> sortOfNumber (List<File> filesOrDirs) {
        //сортировка списка файлов, с именем, содержащим число, по возрастанию числа
        List<File> numbersInFileNames = new ArrayList<>();

        for (File element : filesOrDirs) {
            if (isNumber(element.getName())) {
                numbersInFileNames.add(element);
            }
        }
        if (numbersInFileNames.isEmpty() || numbersInFileNames.size() == 1) return filesOrDirs;

        for (int j = 0; j < numbersInFileNames.size()- 1; j++) {
            for (int i = 0; i < numbersInFileNames.size() - 1; i++) {
                if (numberInFileName(numbersInFileNames.get(i).getName()) > numberInFileName(numbersInFileNames.get(i + 1).getName())) {
                    swap(i, i + 1, numbersInFileNames);
                }
            }
        }
        return numbersInFileNames;
    }

    private int numberInFileName (String name) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(name);
        int result = 0;
        int start = 0;
        while (matcher.find(start)) {
            String value = name.substring(matcher.start(), matcher.end());
            result = Integer.parseInt(value);
            start = matcher.end();
        }
        return result;
    }


    private List<File> swap(int x, int y, List <File> files) {
        // меняет местами элементы списка
        File temp = files.get(x);
        files.set(x, files.get(y));
        files.set(y, temp);
        return files;
    }

    private boolean isNumber(String str) {
        //проверяет, есть ли в имени файла число
        for (char symbol : str.toCharArray()) {
            if (Character.isDigit(symbol)) return true;
        }
        return false;
    }
}
