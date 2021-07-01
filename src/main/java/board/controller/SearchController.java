package board.controller;

import board.dto.Page;
import board.dto.Search;
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
  public String search(Model model, Search search, Page page) {
    model.addAttribute("search", search);
    model.addAttribute("post", searchService.search(search));
    model.addAttribute("page", searchService.setPage(search, page));
    return "search";
  }
}
