package demo;

import demo.model.Meetup;
import demo.model.MeetupRepository;
import demo.model.Member;
import demo.model.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Controller
public class MeetupController {

    MeetupRepository meetupRepo;
    MemberRepository memberRepo;

    @Autowired
    public MeetupController(MeetupRepository meetupRepo, MemberRepository memberRepo) {
        this.meetupRepo = meetupRepo;
        this.memberRepo = memberRepo;
    }

    @RequestMapping("/meetup")
    @ResponseBody
    public HttpEntity<Meetup.Resource> meetup(
            @RequestParam(value = "id", required = true) Long id) {

        Meetup meetup = meetupRepo.findOne(id);

        if(meetup == null) {
            return new ResponseEntity<Meetup.Resource>(HttpStatus.NOT_FOUND);
        }

        Meetup.Resource resource = meetup.toResource();

        resource.add(linkTo(methodOn(MeetupController.class).meetup(id)).withSelfRel());

        

        return new ResponseEntity<Meetup.Resource>(resource, HttpStatus.OK);


    }

    @RequestMapping("/member")
    @ResponseBody
    public HttpEntity<Member.Resource> member(
            @RequestParam(value = "id", required = true) Long id) {

        Member member = memberRepo.findOne(id);

        if(member == null) {
            return new ResponseEntity<Member.Resource>(HttpStatus.NOT_FOUND);
        }

        Member.Resource resource = member.toResource();

        return new ResponseEntity<Member.Resource>(resource, HttpStatus.OK);

    }


}
