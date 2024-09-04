package ChessWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StaticController {
    @GetMapping("/pages/{pageName}")
    public String getHtmlPage(@PathVariable String pageName) throws Exception {
        return pageName;
    }
}