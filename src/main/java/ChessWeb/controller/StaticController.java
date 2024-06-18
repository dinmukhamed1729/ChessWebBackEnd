package ChessWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller		
public class StaticController {
    @GetMapping("/{pageName}")
    public String getHtmlPage(@PathVariable String pageName) {
        return pageName;
    }

  /*  @GetMapping("static/js/{fileName}")
    public String registration(@PathVariable String fileName){
        return "fileName";
    } */

}