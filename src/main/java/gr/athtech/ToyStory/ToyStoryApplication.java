package gr.athtech.ToyStory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToyStoryApplication {

    private static boolean firstTime = true;

    public static void main(String[] args) {
        SpringApplication.run(ToyStoryApplication.class, args);
    }

    public static boolean isFirstTime() {
        return firstTime;
    }

    public static void setFirstTime(boolean firstTime) {
        ToyStoryApplication.firstTime = firstTime;
    }
}
