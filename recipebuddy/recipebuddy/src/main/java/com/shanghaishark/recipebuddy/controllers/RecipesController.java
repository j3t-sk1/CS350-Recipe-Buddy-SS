package com.shanghaishark.recipebuddy.controllers;

import java.util.List;
import java.util.Random;

import org.hibernate.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanghaishark.recipebuddy.business.RecipesBusinessService;
import com.shanghaishark.recipebuddy.models.Recipe;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class RecipesController {
    
    @Autowired
    RecipesBusinessService rbs;

    public int a;
    public int b;
    public int c;
    public int d;
    
    public int num = 0;
    
    @GetMapping("/index")
    public String showHome(Model model){

        Random rand = new Random();
        
        if(rbs.getRecipes().size()>1){
            a = rand.nextInt(rbs.getRecipes().size()-1);
            b = rand.nextInt(rbs.getRecipes().size()-1);
            c = rand.nextInt(rbs.getRecipes().size()-1);
            d = rand.nextInt(rbs.getRecipes().size()-1);
        }
        else{
            a = 0;
            b = 0;
            c = 0;
            d = 0;
        }

        if(rbs.getRecipes().isEmpty()){
            model.addAttribute("randOne", new Recipe());
            model.addAttribute("randTwo", new Recipe());
            model.addAttribute("randThree", new Recipe());
            model.addAttribute("randFour", new Recipe());
    
            model.addAttribute("randOneName", "");
            model.addAttribute("randTwoName", "");
            model.addAttribute("randThreeName", "");
            model.addAttribute("randFourName", "");

            model.addAttribute("recentOne", new Recipe());
            model.addAttribute("recentTwo", new Recipe());
            model.addAttribute("recentThree", new Recipe());
            model.addAttribute("recentFour", new Recipe());
    
            model.addAttribute("recentOneName", "");
            model.addAttribute("recentTwoName", "");
            model.addAttribute("recentThreeName", "");
            model.addAttribute("recentFourName", "");

            model.addAttribute("ratedOne", new Recipe());
            model.addAttribute("ratedTwo", new Recipe());
            model.addAttribute("ratedThree", new Recipe());
            model.addAttribute("ratedFour", new Recipe());
    
            model.addAttribute("ratedOneName", "");
            model.addAttribute("ratedTwoName", "");
            model.addAttribute("ratedThreeName", "");
            model.addAttribute("ratedFourName", "");
        }

        else{
        model.addAttribute("randOne", rbs.getRecipes().get(a));
        model.addAttribute("randTwo", rbs.getRecipes().get(b));
        model.addAttribute("randThree", rbs.getRecipes().get(c));
        model.addAttribute("randFour", rbs.getRecipes().get(d));

        model.addAttribute("randOneName", rbs.getRecipes().get(a).getRecipeName());
        model.addAttribute("randTwoName", rbs.getRecipes().get(b).getRecipeName());
        model.addAttribute("randThreeName", rbs.getRecipes().get(c).getRecipeName());
        model.addAttribute("randFourName", rbs.getRecipes().get(d).getRecipeName());

        if(rbs.getRecipes().size()>0)
            model.addAttribute("recentOne", rbs.getRecent().get(0));
        if(rbs.getRecipes().size()>1)
            model.addAttribute("recentTwo", rbs.getRecent().get(1));
        if(rbs.getRecipes().size()>2)
        model.addAttribute("recentThree", rbs.getRecipes().get(2));
        if(rbs.getRecipes().size()>3)
            model.addAttribute("recentFour", rbs.getRecent().get(3));

        if(rbs.getRecipes().size()>0)
            model.addAttribute("recentOneName", rbs.getRecent().get(0).getRecipeName());
        if(rbs.getRecipes().size()>1)
            model.addAttribute("recentTwoName", rbs.getRecent().get(1).getRecipeName());
        if(rbs.getRecipes().size()>2)
            model.addAttribute("recentThreeName", rbs.getRecent().get(2).getRecipeName());
        if(rbs.getRecipes().size()>3)
            model.addAttribute("recentFourName", rbs.getRecent().get(3).getRecipeName());

        if(rbs.getRecipes().size()>0)
            model.addAttribute("ratedOne", rbs.orderByRating().get(rbs.orderByRating().size()-1));
        if(rbs.getRecipes().size()>1)
            model.addAttribute("ratedTwo", rbs.orderByRating().get(rbs.orderByRating().size()-2));
        if(rbs.getRecipes().size()>2)
            model.addAttribute("ratedThree", rbs.orderByRating().get(rbs.orderByRating().size()-3));
        if(rbs.getRecipes().size()>3)
            model.addAttribute("ratedFour", rbs.orderByRating().get(rbs.orderByRating().size()-1));

        if(rbs.getRecipes().size()>0)
            model.addAttribute("ratedOneName", rbs.orderByRating().get(rbs.orderByRating().size()-1).getRecipeName());
        if(rbs.getRecipes().size()>1)
            model.addAttribute("ratedTwoName", rbs.orderByRating().get(rbs.orderByRating().size()-2).getRecipeName());
        if(rbs.getRecipes().size()>2)
            model.addAttribute("ratedThreeName", rbs.orderByRating().get(rbs.orderByRating().size()-3).getRecipeName());
        if(rbs.getRecipes().size()>3)
            model.addAttribute("ratedFourName", rbs.orderByRating().get(rbs.orderByRating().size()-1).getRecipeName());
        }

        return "index";
    }

    @GetMapping("/adding-recipe")
    public String showAdd(Model model){
        Recipe toAdd = new Recipe();
        model.addAttribute("toAdd", toAdd);
        
        return "adding-recipe";
    }

    @PostMapping("/adding-recipe")
    public String submitAdd(@ModelAttribute Recipe toAdd, Model model){
        rbs.addOne(toAdd);
        return "redirect:index";
    }
    
    @RequestMapping(value="/editing-recipe", method=RequestMethod.GET)
    public String showEdit(Model model, @RequestParam(name="id", required=true, defaultValue = "") String id){
        model.addAttribute("recipeToEdit", rbs.getById(Integer.parseInt(id)));
        model.addAttribute("name", rbs.getById(Integer.parseInt(id)).getRecipeName());
        model.addAttribute("ingredients", rbs.getById(Integer.parseInt(id)).getIngredients());
        model.addAttribute("instructions", rbs.getById(Integer.parseInt(id)).getInstructions());
        model.addAttribute("rate", rbs.getById(Integer.parseInt(id)).getChefRate());
        model.addAttribute("utensils", rbs.getById(Integer.parseInt(id)).getUtensils());
        model.addAttribute("serveSize", rbs.getById(Integer.parseInt(id)).getServeSize());
        model.addAttribute("temp", rbs.getById(Integer.parseInt(id)).getoTemp());
        model.addAttribute("pic", rbs.getById(Integer.parseInt(id)).getPic());
        model.addAttribute("id", Integer.parseInt(id));

        return "editing-recipe";
    }

    
    @PostMapping("editing-recipe")
    public String submitEdit(Recipe recipe, BindingResult bindingResult, Model model){
        rbs.updateOne(recipe.getId(), recipe);
        return "redirect:index";
    }

    @RequestMapping(value="/search", method=RequestMethod.GET)
    public String searchResults(Model model, @RequestParam(value="search", required=true, defaultValue = "") String queryParam){
        String keyword = queryParam;
        List<Recipe> searchResults = rbs.searchRecipes(keyword);
        model.addAttribute("searchResults", searchResults);
        return "search";
    }


    @GetMapping("/delete2")
    public String showDelete(Recipe toDelete, Model model){
        List<Recipe> allRecipes = rbs.getRecipes();
        model.addAttribute("allRecipes", allRecipes);
        return "delete2";
    }

    @GetMapping("/delete2/submit")
    public String submitDelete(Recipe toDelete, BindingResult bindingresult, Model model){
        rbs.deleteOne(toDelete.getId());
        return "redirect:http://localhost:8080/index";
    }

    @GetMapping("/random1")
    public String showRand1(Model model){
        Recipe rand1 = rbs.getRecipes().get(a);
        model.addAttribute("rand1", rand1);
        model.addAttribute("name", rand1.getRecipeName());
        model.addAttribute("ingredients", rand1.getIngredients());
        model.addAttribute("utensils", rand1.getUtensils());
        model.addAttribute("rate", rand1.getChefRate());
        model.addAttribute("instructions", rand1.getInstructions());
        model.addAttribute("serveSize", rand1.getServeSize());
        model.addAttribute("temp", rand1.getoTemp());
        model.addAttribute("pic", rand1.getPic());
        
        return "random1";
    }

    @GetMapping("/random2")
    public String showRand2(Model model){
        Recipe rand2 = rbs.getRecipes().get(b);
        model.addAttribute("rand2", rand2);
        model.addAttribute("name", rand2.getRecipeName());
        model.addAttribute("ingredients", rand2.getIngredients());
        model.addAttribute("utensils", rand2.getUtensils());
        model.addAttribute("rate", rand2.getChefRate());
        model.addAttribute("instructions", rand2.getInstructions());
        model.addAttribute("serveSize", rand2.getServeSize());
        model.addAttribute("temp", rand2.getoTemp());
        model.addAttribute("pic", rand2.getPic());
        
        return "random2";
    }

    @GetMapping("/random3")
    public String showRand3(Model model){
        Recipe rand3 = rbs.getRecipes().get(c);
        model.addAttribute("rand3", rand3);
        model.addAttribute("name", rand3.getRecipeName());
        model.addAttribute("ingredients", rand3.getIngredients());
        model.addAttribute("utensils", rand3.getUtensils());
        model.addAttribute("rate", rand3.getChefRate());
        model.addAttribute("instructions", rand3.getInstructions());
        model.addAttribute("serveSize", rand3.getServeSize());
        model.addAttribute("temp", rand3.getoTemp());
        model.addAttribute("pic", rand3.getPic());
        return "random3";
    }

    @GetMapping("/random4")
    public String showRand4(Model model){
        Recipe rand4 = rbs.getRecipes().get(d);
        model.addAttribute("rand4", rand4);
        model.addAttribute("name", rand4.getRecipeName());
        model.addAttribute("ingredients", rand4.getIngredients());
        model.addAttribute("utensils", rand4.getUtensils());
        model.addAttribute("rate", rand4.getChefRate());
        model.addAttribute("instructions", rand4.getInstructions());
        model.addAttribute("serveSize", rand4.getServeSize());
        model.addAttribute("temp", rand4.getoTemp());
        model.addAttribute("pic", rand4.getPic());
        
        return "random4";
    }


    @GetMapping("/recent1")
    public String showRecent1(Model model){
        Recipe recent1 = rbs.getRecipes().get(rbs.getRecipes().size()-1);
        model.addAttribute("recent1", recent1);
        model.addAttribute("name", recent1.getRecipeName());
        model.addAttribute("ingredients", recent1.getIngredients());
        model.addAttribute("utensils", recent1.getUtensils());
        model.addAttribute("rate", recent1.getChefRate());
        model.addAttribute("instructions", recent1.getInstructions());
        model.addAttribute("serveSize", recent1.getServeSize());
        model.addAttribute("temp", recent1.getoTemp());
        model.addAttribute("pic", recent1.getPic());
        
        return "recent1";
    }

    @GetMapping("/recent2")
    public String showRecent2(Model model){
        Recipe recent2 = rbs.getRecipes().get(rbs.getRecipes().size()-2);
        model.addAttribute("recent2", recent2);
        model.addAttribute("name", recent2.getRecipeName());
        model.addAttribute("ingredients", recent2.getIngredients());
        model.addAttribute("utensils", recent2.getUtensils());
        model.addAttribute("rate", recent2.getChefRate());
        model.addAttribute("instructions", recent2.getInstructions());
        model.addAttribute("serveSize", recent2.getServeSize());
        model.addAttribute("temp", recent2.getoTemp());
        model.addAttribute("pic", recent2.getPic());
        
        return "recent2";
    }

    @GetMapping("/recent3")
    public String showRecent3(Model model){
        Recipe recent3 = rbs.getRecipes().get(rbs.getRecipes().size()-3);
        model.addAttribute("recent3", recent3);
        model.addAttribute("name", recent3.getRecipeName());
        model.addAttribute("ingredients", recent3.getIngredients());
        model.addAttribute("utensils", recent3.getUtensils());
        model.addAttribute("rate", recent3.getChefRate());
        model.addAttribute("instructions", recent3.getInstructions());
        model.addAttribute("serveSize", recent3.getServeSize());
        model.addAttribute("temp", recent3.getoTemp());
        model.addAttribute("pic", recent3.getPic());

        return "recent3";
    }

    @GetMapping("/recent4")
    public String showRecent4(Model model){
        Recipe recent4 = rbs.getRecipes().get(rbs.getRecipes().size()-4);
        model.addAttribute("recent4", recent4);
        model.addAttribute("name", recent4.getRecipeName());
        model.addAttribute("ingredients", recent4.getIngredients());
        model.addAttribute("utensils", recent4.getUtensils());
        model.addAttribute("rate", recent4.getChefRate());
        model.addAttribute("instructions", recent4.getInstructions());
        model.addAttribute("serveSize", recent4.getServeSize());
        model.addAttribute("temp", recent4.getoTemp());
        model.addAttribute("pic", recent4.getPic());
        
        return "recent4";
    }

    @GetMapping("/rated1")
    public String showRated1(Model model){
        Recipe rated1 = new Recipe();
        if(rbs.orderByRating().size()>1)
            rated1 = rbs.orderByRating().get(rbs.orderByRating().size()-1);
        model.addAttribute("rated1", rated1);
        model.addAttribute("name", rated1.getRecipeName());
        model.addAttribute("ingredients", rated1.getIngredients());
        model.addAttribute("instructions", rated1.getInstructions());
        model.addAttribute("rate", rated1.getChefRate());
        model.addAttribute("utensils", rated1.getUtensils());
        model.addAttribute("serveSize", rated1.getServeSize());
        model.addAttribute("temp", rated1.getoTemp());
        model.addAttribute("pic", rated1.getPic());
        
        return "rated1";
    }

    @GetMapping("/rated2")
    public String showRated2(Model model){
        Recipe rated2 = new Recipe();
        if(rbs.orderByRating().size()>2)
            rated2 = rbs.orderByRating().get(rbs.orderByRating().size()-2);
        model.addAttribute("rated2", rated2);
        model.addAttribute("name", rated2.getRecipeName());
        model.addAttribute("ingredients", rated2.getIngredients());
        model.addAttribute("instructions", rated2.getInstructions());
        model.addAttribute("rate", rated2.getChefRate());
        model.addAttribute("utensils", rated2.getUtensils());
        model.addAttribute("serveSize", rated2.getServeSize());
        model.addAttribute("temp", rated2.getoTemp());
        model.addAttribute("pic", rated2.getPic());
        
        return "rated2";
    }

    @GetMapping("/rated3")
    public String showRated3(Model model){
        Recipe rated3 = new Recipe();
        if(rbs.orderByRating().size()>3)
            rated3 = rbs.orderByRating().get(rbs.orderByRating().size()-3);
        model.addAttribute("rated3", rated3);
        model.addAttribute("name", rated3.getRecipeName());
        model.addAttribute("ingredients", rated3.getIngredients());
        model.addAttribute("instructions", rated3.getInstructions());
        model.addAttribute("rate", rated3.getChefRate());
        model.addAttribute("utensils", rated3.getUtensils());
        model.addAttribute("serveSize", rated3.getServeSize());
        model.addAttribute("temp", rated3.getoTemp());
        model.addAttribute("pic", rated3.getPic());

        return "rated3";
    }

    @GetMapping("/rated4")
    public String showRated4(Model model){
        Recipe rated4 = new Recipe();
        if(rbs.orderByRating().size()>4)
            rated4 = rbs.orderByRating().get(rbs.orderByRating().size()-4);
        model.addAttribute("rated4", rated4);
        model.addAttribute("name", rated4.getRecipeName());
        model.addAttribute("ingredients", rated4.getIngredients());
        model.addAttribute("instructions", rated4.getInstructions());
        model.addAttribute("rate", rated4.getChefRate());
        model.addAttribute("utensils", rated4.getUtensils());
        model.addAttribute("serveSize", rated4.getServeSize());
        model.addAttribute("temp", rated4.getoTemp());
        model.addAttribute("pic", rated4.getPic());
        
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

    @RequestMapping(value="/recipe-viewing", method=RequestMethod.GET)
    public String showRecipeView(Model model, @RequestParam(value="id", required=true, defaultValue = "") String queryParam){
        model.addAttribute("recipeToView", rbs.getById(Integer.parseInt(queryParam)));
        model.addAttribute("name", rbs.getById(Integer.parseInt(queryParam)).getRecipeName());
        model.addAttribute("ingredients", rbs.getById(Integer.parseInt(queryParam)).getIngredients());
        model.addAttribute("instructions", rbs.getById(Integer.parseInt(queryParam)).getInstructions());
        model.addAttribute("rate", rbs.getById(Integer.parseInt(queryParam)).getChefRate());
        model.addAttribute("utensils", rbs.getById(Integer.parseInt(queryParam)).getUtensils());
        model.addAttribute("serveSize", rbs.getById(Integer.parseInt(queryParam)).getServeSize());
        model.addAttribute("temp", rbs.getById(Integer.parseInt(queryParam)).getoTemp());
        model.addAttribute("pic", rbs.getById(Integer.parseInt(queryParam)).getPic());
        return "recipe-viewing";
    }

    @GetMapping("/user_recipes")
    public String showUserRecipes(Model model){
        List<Recipe> allRecipes = rbs.getRecipes();
        model.addAttribute("allRecipes", allRecipes);
        return "user_recipes";
    }

    @GetMapping("/edit")
    public String showEdit(Model model){
        List<Recipe> allRecipes = rbs.getRecipes();
        model.addAttribute("allRecipes", allRecipes);
        return "edit";
    }

    @GetMapping("/settings")
    public String showSettings(Model model){
        return "settings";
    }

    @GetMapping("/")
    public String landing(Model model){
        return "redirect:index";
    }
}

