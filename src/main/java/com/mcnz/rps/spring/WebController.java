package com.mcnz.rps.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebController {

	@GetMapping("/playagame")
	public String playRoshambo(@RequestParam(name = "choice", required=false) String choice, Model model) {
		System.out.println("In play");
		if (choice == null) {
			return "index";
		}
		System.out.println("Choice: " + choice);

		String result = "tie";
		if (choice.equals("paper")) {
			result = "win";
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject("http://localhost:8080/score/wins", "", Object.class);
		}
		if (choice.equals("scissors")) {
			result = "lose";
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject("http://localhost:8080/score/losses", "", Object.class);
		}
		if (choice.equals("rock")) {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject("http://localhost:8080/score/ties", "", Object.class);
		}


		RestTemplate restTemplate = new RestTemplate();
		Score score = restTemplate.getForObject("http://localhost:8080/score", Score.class);


		model.addAttribute("score", score);
		// request.getRequestDispatcher("index.jsp").forward(request, response);

		return "results";
	}
	
	@GetMapping("/index")
	public String index(@RequestParam(name = "choice", required=false) String choice, Model model) {
		return "index";
	}
	
	

}
