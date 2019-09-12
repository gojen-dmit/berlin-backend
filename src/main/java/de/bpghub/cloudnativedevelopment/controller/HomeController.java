package de.bpghub.cloudnativedevelopment.controller;

import de.bpghub.cloudnativedevelopment.counter.Counter;
import de.bpghub.cloudnativedevelopment.counter.CounterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private CounterRepository counterRepository;

    @GetMapping("/")
    public String homeTemplate(Model model) {

        Counter berlin = counterRepository.findByName("berlin");
        if (berlin == null) {
            berlin = new Counter();
            berlin.setName("berlin");
        }
        berlin.setCount(berlin.getCount() + 1);
        counterRepository.save(berlin);

        model.addAttribute("hostname", getHostname());
        model.addAttribute("berlin", berlin);

        log.info("You are Visitor Number {} on {}", berlin.getCount(), getHostname());
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
