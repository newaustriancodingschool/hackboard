package io.refugeescode.hackboard.initialise;


import io.refugeescode.hackboard.model.Projects;
import io.refugeescode.hackboard.parser.Parser;
import io.refugeescode.hackboard.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationStarter {

    @Autowired
    Parser parser;


    @Bean
    ApplicationRunner applicationRunner(ProjectsRepository projectsRepository){
        return applicationArguments ->{

          projectsRepository.deleteAll();
          String fileName="src\\main\\java\\io\\refugeescode\\hackboard\\data\\project1";
          Projects allData = parser.getAllData(fileName);
            System.out.println(allData);
          projectsRepository.save(allData);

        };
    }

}
