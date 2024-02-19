package com.example.inventorymanagement.controller;//package com.example.inventorymanagement.controller;
//
//import com.example.inventorymanagement.model.Item;
//import com.example.inventorymanagement.model.Product;
//import com.example.inventorymanagement.service.item.IItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping("/items")
//public class ItemController {
//    @Autowired
//    private IItemService iItemService;
//
//    @GetMapping
//    public ModelAndView homeProduct() {
//        ModelAndView modelAndView = new ModelAndView("/items/home");
//        modelAndView.addObject("items", iItemService.findAll());
//        return modelAndView;
//    }
//
//    @GetMapping("/create")
//    public ModelAndView getCreate() {
//        ModelAndView modelAndView = new ModelAndView("/items/save");
//        modelAndView.addObject("item", new Product());
//        return modelAndView;
//    }
//
//    @PostMapping("/create")
//    public String postCreate(Item item) {
//        iItemService.save(item);
//        return "redirect:/items";
//    }
//
//    @GetMapping("/update/{id}")
//    public ModelAndView getUpdate(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("/items/save");
//        modelAndView.addObject("item", iItemService.findById(id));
//        return modelAndView;
//    }
//
//    @PostMapping("/create/{id}")
//    public String postUpdate(@PathVariable Long id, Item item) {
//        item.setId(id);
//        iItemService.save(item);
//        return "redirect:/items";
//    }
//
//}
