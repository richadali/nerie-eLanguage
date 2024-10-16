//package nerie.e_resources.non_schedule.controllers;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import nerie.e_resources.non_schedule.entity.Category;
//import nerie.e_resources.non_schedule.services.WordService;
//
//@Controller
//@RequestMapping("/nerie/e-resources/")
//
//public class WordsController {
//
//	@Autowired
//	private WordService wordService;
//
//	// Add new category Page
//	@GetMapping(value = "/add-category")
//	public String add_category() {
//		return "add-category";
//	}
//
//	// Get categories Page
//	@GetMapping("/get-categories")
//	public String get_category() {
//		return "get-categories";
//	}
//
//	// Add new Category
//	@PostMapping("/add-category")
//	public ResponseEntity<Object> add_category(@RequestBody Map<String, String> word) {
//		wordService.addCategory(word.get("category"));
//
//		return ResponseEntity.status(HttpStatus.OK).body("Added Succesfully!");
//	}
//
//	// Edit Category
//	@PostMapping("/edit-category")
//	public ResponseEntity<Object> edit_category(@RequestBody Category category) {
//		try {
//			wordService.editCategory(category);
//		} catch (Exception e) {
//			System.out.println(e);
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update");
//		}
//
//		return ResponseEntity.status(HttpStatus.OK).body("Added Succesfully!");
//	}
//
//	// Getting List of all Categories
//	@PostMapping(value = "/get-list-categories")
//
//	public @ResponseBody ResponseEntity<String> getListCategories() throws IOException {
//
//		List<Category> categories = null;
//		categories = wordService.getListCategories();
//
//		return ResponseEntity.status(HttpStatus.OK)
//				.body("/*" + new ObjectMapper().writeValueAsString(categories) + "*/");
//	}
//
//}
