package io.refugeescode.hackboard.parser;

import io.refugeescode.hackboard.domain.Project;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Parser {

        public Project getAllData(String fileName){
            Project project = new Project();
            List<String> list = null;
            try {
                list = Files.readAllLines(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
                project.setTitle(list.get(0));

            StringBuilder stringBuilder = new StringBuilder();
            list.stream()
                .skip(0)
                .forEach(line->stringBuilder.append(line));

            project.setDescription(stringBuilder.toString());
            return project;
        }


}
