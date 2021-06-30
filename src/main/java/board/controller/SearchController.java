package board.controller;

import board.dto.SearchDto;
import board.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

  private final SearchService searchService;

  public SearchController(SearchService searchService) {
    this.searchService = searchService;
  }

  @GetMapping(value = "/search")
  public String search(Model model, SearchDto searchDto) {
    model.addAttribute("search", searchService.search(searchDto));
    return "search";
  }
}
