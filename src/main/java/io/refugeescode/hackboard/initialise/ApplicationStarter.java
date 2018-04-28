package io.refugeescode.hackboard.initialise;


import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.parser.Parser;
import io.refugeescode.hackboard.repository.ProjectRepository;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationStarter {

    @Autowired
    private Parser parser;


    @Bean
    ApplicationRunner applicationRunner(ProjectRepository projectRepository){
        return applicationArguments ->{

          projectRepository.deleteAll();
          String fileName="src/main/java/io/refugeescode/hackboard/data/project1";
          Project allData = parser.getAllData(fileName);
            System.out.println(allData);
          projectRepository.save(allData);

        };
    }

}
