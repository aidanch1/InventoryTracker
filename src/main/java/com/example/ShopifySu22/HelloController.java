package com.example.ShopifySu22;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Controller
public class HelloController {

    @Autowired
    Repository repository;

    @RequestMapping("/")
    public String helloMessage(Model model) {
        model.addAttribute("items", repository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addItem(WebRequest request) {
        Item item = new Item(request.getParameter("name"),
                Double.parseDouble(request.getParameter("price")), Integer.parseInt(request.getParameter("count")));
        repository.save(item);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable("id") long id, Model model) {
        Item item = repository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        model.addAttribute("editing", item);
        model.addAttribute("items", repository.findAll());
        return "index";
    }
    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable("id") long id, Item updatedItem, Model model) {
        repository.save(updatedItem);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/export")
    public void exportData(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"inventory.csv\"");
        Writer writer = servletResponse.getWriter();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord("name", "price", "#in stock");
        for (Item item : repository.findAll()) {
            csvPrinter.printRecord(item.getName(), item.getPrice(), item.getCount());
        }
        csvPrinter.flush();
    }
}
