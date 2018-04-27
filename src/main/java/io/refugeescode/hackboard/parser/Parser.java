package io.refugeescode.hackboard.parser;

import io.refugeescode.hackboard.model.Projects;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class Parser {

        public Projects getAllData(String fileName){
            Projects Projects = new Projects();
            List<String> list = null;
            try {
                list = Files.readAllLines(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
                Projects.setTitle(list.get(0));

            StringBuilder stringBuilder = new StringBuilder();
            list.stream()
                .skip(0)
                .forEach(line->stringBuilder.append(line));

            Projects.setDescription(stringBuilder.toString());
            return Projects;
        }


}
