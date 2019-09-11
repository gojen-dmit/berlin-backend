package de.bpghub.springbootdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Controller
public class HelloWorldController {

    @Autowired
    private CounterRepository counterRepository;

    @GetMapping("/")
    public String greetingTemplate(Model model) {

        Counter berlin = counterRepository.findByName("berlin");
        if (berlin == null) {
            berlin = new Counter();
            berlin.setName("berlin");
        }
        berlin.setCount(berlin.getCount() + 1);
        counterRepository.save(berlin);

        model.addAttribute("hostname", getHostname());
        model.addAttribute("berlin", berlin);

        String greeting = "Hello, world from " + getHostname() + ". Counter: " + berlin.getCount();
        log.info("{}", greeting);
        return "home";
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "unknown";
    }

}
