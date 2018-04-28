package io.refugeescode.hackboard.parser;

import io.refugeescode.hackboard.model.Project;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class Parser {

        public Project getAllData(String fileName){
            Project Project = new Project();
            List<String> list = null;
            try {
                list = Files.readAllLines(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
                Project.setTitle(list.get(0));

            StringBuilder stringBuilder = new StringBuilder();
            list.stream()
                .skip(0)
                .forEach(line->stringBuilder.append(line));

            Project.setDescription(stringBuilder.toString());
            return Project;
        }


}
