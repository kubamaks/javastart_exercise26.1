package pl.km.exercise261;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Time;

@SpringBootApplication
public class Exercise261Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Exercise261Application.class, args);
        RecipeRepository recipeRepository = context.getBean(RecipeRepository.class);
        RecipeCategoryRepository recipeCategoryRepository = context.getBean(RecipeCategoryRepository.class);
        InitialDataProvider.provideInitialData(recipeRepository, recipeCategoryRepository);

    }
}
