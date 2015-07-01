package demo;

import demo.model.Meetup;
import demo.model.MeetupRepository;
import demo.model.Member;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public InitializingBean createTestData(MeetupRepository r) {
        return () -> {

            Meetup m1 = new Meetup(1l, "An exciting meetup");
            m1.setAttendees(Arrays.asList(
                    new Member(m1, 1l, "Ani"),
                    new Member(m1, 2l, "Daniel"),
                    new Member(m1, 3l, "Kon")
            ));
            r.save(m1);

            Meetup m2 = new Meetup(2l, "Another exciting meetup");
            m2.setAttendees(Arrays.asList(
                    new Member(m2, 4l, "Davide"),
                    new Member(m2, 5l, "Rashmi"),
                    new Member(m2, 6l, "Nasrin")
            ));

            r.save(m2);
            r.flush();

        };
    }
}
