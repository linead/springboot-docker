package demo;

import demo.model.Meetup;
import demo.model.MeetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("/list")
public class DemoController {

    private final MeetupRepository repository;

    @Autowired
    public DemoController(MeetupRepository repository) {
        this.repository = repository;
    }

    @RequestMapping
    public ModelAndView list() {
        Iterable<Meetup> meetups = this.repository.findAll();
        return new ModelAndView("list", "meetups", meetups);
    }
}
