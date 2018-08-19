package classes.controllers;


import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import classes.scrapping.Report;
import classes.services.Service;
import classes.utils.JSONConverter;
import classes.utils.Server;

@Controller
public class KeywordController {

    public static Server server = new Server();
    private  Service keywordService = new Service();

    /**
     * Return the index page
     * @return
     */
	@RequestMapping("/")
	public String getIndex() {
		return "index";
	}
    
    /**
     * Returns the result of a keyword search in a JSON format
     */
    @GetMapping("/getInfoByKeyword")
    @ResponseBody
    public String getInfoByKeyword(@RequestParam(required = true) String keyword, Model model) {
        String fileContent =  keywordService.getKeywordInfo(keyword); 
        Report report  = JSONConverter.getReport(fileContent);
        return report.toJson();
    }
    
    /**
     * Returns a view which the user can see the result of a keyword that has been searched
     */
    @GetMapping("/getResultViewByKeyword")
    public String getResultViewByKeyword(@RequestParam(required = true) String keyword, Model model) {
        String fileContent =  keywordService.getKeywordInfo(keyword); 
        Report report  = JSONConverter.getReport(fileContent);
        model.addAttribute("report", report);
        return "keyword";
    }    
    

    /**
     * Starts a searching by the keyword specified
     */
    @GetMapping("/startSearchByKeyword")
    public void searchByKeyword(@RequestParam(required = true) String keyword, @RequestParam(required = false) Integer period) {
    	period = Optional.ofNullable(period).orElse(5); // 5 minutes if the time is not set
    	keywordService.startSearch(keyword, period);     
    }

    
}
