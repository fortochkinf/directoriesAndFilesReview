package com.blckRbbit.server.controller;

import com.blckRbbit.server.entity.DirectoryEntity;
import com.blckRbbit.server.entity.FileEntity;
import com.blckRbbit.server.exeption.PathNotFoundException;
import com.blckRbbit.server.service.DirectoryService;
import com.blckRbbit.server.service.FileService;
import com.blckRbbit.server.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class MainController {

    @Autowired
    private DirectoryService directoryService;

    @Autowired
    private FileService fileService;

    @Autowired
    private MainService mainService;

    @GetMapping("/")
    public String index(Model model)  {
        model.addAttribute("directories", mainService.showDirectory());
        model.addAttribute("files", mainService.showFiles());
        return "index";
    }

    @PostMapping("/")
    public String addPath(@RequestParam String path) throws PathNotFoundException {
        DirectoryEntity directory = new DirectoryEntity(path);
        directoryService.register(directory);
        FileEntity files = new FileEntity(directory);
        fileService.register(files);
        return "redirect:/";
    }

    @PostMapping
    public static String error(@RequestParam String userEnter) {
        return "redirect:/error";
    }
}
