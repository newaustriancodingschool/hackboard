package io.refugeescode.hackboard.initialise;


import io.refugeescode.hackboard.domain.Project;
import io.refugeescode.hackboard.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationStarter {

    @Autowired
    private ProjectRepository projectRepository;


    @Bean
    ApplicationRunner applicationRunner(){
        return applicationArguments ->{

               projectRepository.deleteAll();
               initialdata();


        };

    }

    void initialdata(){

        String title1 = "Text-Based Role-Play-Game RPG";

        StringBuilder  description1 = new StringBuilder();
        description1.append("Purpose: A game around which we can build our coding school.");
        description1.append("What’s the problem: What if we could create a simple text-based game, where learning becomes a journey? ");
        description1.append("Where we have a character that levels up, collects points, money, purchases new equipment, but most of all: ");
        description1.append("writes lines of code. Games like this can be addictive, give structure, help observe progress, bring people");
        description1.append("together and much more. And the best thing is: we can learn by building our own infrastructure!");

        Project project1 = new Project();
        project1.setId((long) 1);
        project1.setTitle(title1);
        project1.setDescription(description1.toString());
        projectRepository.save(project1);


        String title2 = "A Treasure Hunt Map";

        StringBuilder  description2 = new StringBuilder();
        description2.append("Purpose: A map with things to do.");
        description2.append("What’s the problem: It’s hard to coordinate group efforts, especially when it’s about doing something at  ");
        description2.append("different locations. Using a map, we could create “checkpoints” where information must be gathered or ");
        description2.append("something must be updated. Can we build a map with for example red markers that a user can turn green once ");
        description2.append("certain information has been found.");

        Project project2 = new Project();
        project2.setId((long) 2);
        project2.setTitle(title2);
        project2.setDescription(description2.toString());
        projectRepository.save(project2);



        String title3 = "Klasse & Kasse, crowd-function for school projects";

        StringBuilder  description3 = new StringBuilder();
        description3.append("Purpose: building a crowdfunding platform for school projects");
        description3.append("What’s the problem: Teachers are our heroes, they shape the coming generations. The best teachers organise ");
        description3.append("all kinds of extra educational events. Let’s help them: Collecting money from parents is a pain in the back. Also, ");
        description3.append("allowing for rich parents to give more, and less wealthy to give less, can be possible. And finally, if we already ");
        description3.append("have interested parents on a crowd-funding site, they may even donate for other great projects at the school or ");
        description3.append("national level.");

        Project project3 = new Project();
        project3.setId((long) 3);
        project3.setTitle(title3);
        project3.setDescription(description3.toString());
        projectRepository.save(project3);


    }
}
