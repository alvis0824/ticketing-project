package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }


    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers",userService.findManagers());
        model.addAttribute("projects",projectService.findAll());

        return "/project/create";
    }

    @PostMapping("/create")
    public String insertProject(@ModelAttribute("project") ProjectDTO project){

        projectService.save(project);

        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectCode}")
    public String deleteUser(@PathVariable("projectCode") String projectCode){

        projectService.deleteById(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectCode}")
    public String editProject(@PathVariable("projectCode") String projectCode, Model model){

        model.addAttribute("project",projectService.findById(projectCode));
        model.addAttribute("managers",userService.findManagers());
        model.addAttribute("projects",projectService.findAll());

        return "/project/update";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute("project") ProjectDTO project){
        //update that user
        projectService.update(project);

        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode){

        projectService.complete(projectService.findById(projectCode));
        return "redirect:/project/create";
    }
}
