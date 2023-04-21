package com.shanghaishark.recipebuddy.controllers;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanghaishark.recipebuddy.business.RecipesBusinessService;
import com.shanghaishark.recipebuddy.models.Recipe;


@Controller
public class RecipesController {
    
    @Autowired
    RecipesBusinessService rbs;

    public int a;
    public int b;
    public int c;
    public int d;
    
    @GetMapping("/index")
    public String showHome(Model model){

        Random rand = new Random();
        
        a = rand.nextInt(rbs.getRecipes().size());
        b = rand.nextInt(rbs.getRecipes().size());
        c = rand.nextInt(rbs.getRecipes().size());
        d = rand.nextInt(rbs.getRecipes().size());

        model.addAttribute("randOne", rbs.getRecipes().get(a));
        model.addAttribute("randTwo", rbs.getRecipes().get(b));
        model.addAttribute("randThree", rbs.getRecipes().get(c));
        model.addAttribute("randFour", rbs.getRecipes().get(d));

        model.addAttribute("randOneName", rbs.getRecipes().get(a).getRecipeName());
        model.addAttribute("randTwoName", rbs.getRecipes().get(b).getRecipeName());
        model.addAttribute("randThreeName", rbs.getRecipes().get(c).getRecipeName());
        model.addAttribute("randFourName", rbs.getRecipes().get(d).getRecipeName());

        model.addAttribute("recentOne", rbs.getRecipes().get(rbs.getRecipes().size()-1));
        model.addAttribute("recentTwo", rbs.getRecipes().get(rbs.getRecipes().size()-2));
        model.addAttribute("recentThree", rbs.getRecipes().get(rbs.getRecipes().size()-3));
        model.addAttribute("recentFour", rbs.getRecipes().get(rbs.getRecipes().size()-4));

        model.addAttribute("recentOneName", rbs.getRecipes().get(rbs.getRecipes().size()-1).getRecipeName());
        model.addAttribute("recentTwoName", rbs.getRecipes().get(rbs.getRecipes().size()-2).getRecipeName());
        model.addAttribute("recentThreeName", rbs.getRecipes().get(rbs.getRecipes().size()-3).getRecipeName());
        model.addAttribute("recentFourName", rbs.getRecipes().get(rbs.getRecipes().size()-4).getRecipeName());

        model.addAttribute("ratedOne", rbs.orderByRating().get(rbs.orderByRating().size()-1));
        model.addAttribute("ratedTwo", rbs.orderByRating().get(rbs.orderByRating().size()-2));
        model.addAttribute("ratedThree", rbs.orderByRating().get(rbs.orderByRating().size()-3));
        model.addAttribute("ratedFour", rbs.orderByRating().get(rbs.orderByRating().size()-1));

        model.addAttribute("ratedOneName", rbs.orderByRating().get(rbs.orderByRating().size()-1).getRecipeName());
        model.addAttribute("ratedTwoName", rbs.orderByRating().get(rbs.orderByRating().size()-2).getRecipeName());
        model.addAttribute("ratedThreeName", rbs.orderByRating().get(rbs.orderByRating().size()-3).getRecipeName());
        model.addAttribute("ratedFourName", rbs.orderByRating().get(rbs.orderByRating().size()-1).getRecipeName());

        return "index";
    }

    @GetMapping("/adding-recipe")
    public String showAdd(Model model){
        Recipe toAdd = new Recipe();
        model.addAttribute("toAdd", toAdd);
        
        return "adding-recipe";
    }

    @PostMapping("/adding-recipe")
    public String submitAdd(@ModelAttribute Recipe toAdd){
        rbs.addOne(toAdd);
        return "index";
    }
    
    @GetMapping("/editing-recipe")
    public String showEdit(Recipe toEdit, Model model){
        return "editing-recipe";
    }

    @PostMapping("/editing-recipe")
    public String submitEdit(@ModelAttribute Recipe toEdit){
        rbs.updateOne(toEdit.getRecipeName(), toEdit);
        return "index";
    }

    @GetMapping("/search")
    @RequestMapping("/")
    public String showResults(Model model, @Param ("word") String keyword){
        model.addAttribute("results", rbs.searchRecipes(keyword));
        return "search";
    }

    @GetMapping("/delete2")
    public String showDelete(Recipe toDelete, Model model){
        return "delete2";
    }

    @PostMapping("/delete2")
    public String submitDelete(@ModelAttribute Recipe toDelete){
        rbs.deleteOne(toDelete.getRecipeName());
        return "delete2";
    }

    @GetMapping("/random1")
    public String showRand1(Model model){
        Recipe rand1 = rbs.getRecipes().get(a);
        model.addAttribute("rand1", rand1);
        
        return "random1";
    }

    @GetMapping("/random2")
    public String showRand2(Model model){
        Recipe rand2 = rbs.getRecipes().get(b);
        model.addAttribute("rand2", rand2);
        
        return "random2";
    }

    @GetMapping("/random3")
    public String showRand3(Model model){
        Recipe rand3 = rbs.getRecipes().get(c);
        model.addAttribute("rand3", rand3);
        return "random3";
    }

    @GetMapping("/random4")
    public String showRand4(Model model){
        Recipe rand4 = rbs.getRecipes().get(d);
        model.addAttribute("rand4", rand4);
        
        return "random4";
    }


    @GetMapping("/recent1")
    public String showRecent1(Model model){
        Recipe recent1 = rbs.getRecipes().get(rbs.getRecipes().size()-1);
        model.addAttribute("recent1", recent1);
        
        return "recent1";
    }

    @GetMapping("/recent2")
    public String showRecent2(Model model){
        Recipe recent2 = rbs.getRecipes().get(rbs.getRecipes().size()-2);
        model.addAttribute("recent2", recent2);
        
        return "recent2";
    }

    @GetMapping("/recent3")
    public String showRecent3(Model model){
        Recipe recent3 = rbs.getRecipes().get(rbs.getRecipes().size()-3);
        model.addAttribute("recent3", recent3);
        return "recent3";
    }

    @GetMapping("/recent4")
    public String showRecent4(Model model){
        Recipe recent4 = rbs.getRecipes().get(rbs.getRecipes().size()-4);
        model.addAttribute("recent4", recent4);
        
        return "recent4";
    }

    @GetMapping("/rated1")
    public String showRated1(Model model){
        Recipe rated1 = rbs.orderByRating().get(rbs.orderByRating().size()-1);
        model.addAttribute("rated1", rated1);
        
        return "rated1";
    }

    @GetMapping("/rated2")
    public String showRated2(Model model){
        Recipe rated2 = rbs.orderByRating().get(rbs.orderByRating().size()-2);
        model.addAttribute("rated2", rated2);
        
        return "rated2";
    }

    @GetMapping("/rated3")
    public String showRated3(Model model){
        Recipe rated3 = rbs.orderByRating().get(rbs.orderByRating().size()-3);
        model.addAttribute("rated3", rated3);
        return "rated3";
    }

    @GetMapping("/rated4")
    public String showRated4(Model model){
        Recipe rated4 = rbs.orderByRating().get(rbs.orderByRating().size()-4);
        model.addAttribute("rated4", rated4);
        
        return "rated4";
    }

    @GetMapping("/pantry")
    public String showPantry(Model model){
        return "pantry";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        return "profile";
    }

    @GetMapping("/recipe-viewing")
    public String showRecipeView(Model model){
        return "recipe-viewing";
    }

    @GetMapping("/user-recipes")
    public String showUserRecipes(Model model){
        return "user-recipes";
    }

    @GetMapping("/edit")
    public String showEdit(Model model){
        return "edit";
    }

    @GetMapping("/settings")
    public String showSettings(Model model){
        return "settings";
    }

}
