package com.finabil.quizv2.controller;

import com.finabil.quizv2.model.QuestionForm;
import com.finabil.quizv2.model.Result;
import com.finabil.quizv2.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class QuizController {
    private final QuizService quizService;
    private Result result;
    private Boolean submitted;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
        result = new Result();
        submitted = false;
    }

    @GetMapping("/")
    public String getHomePage(){
        return "HomePage";
    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam String username, RedirectAttributes redirectAttributes, Model model){
        if(username.isEmpty()){
            redirectAttributes.addFlashAttribute("warning", "you must enter your name");
            return "redirect:/";
        }
      //  System.out.println(username);
        submitted = false;
        result = new Result();
        result.setUsername(username);
        QuestionForm questionForm = new QuestionForm();
        questionForm.setQuestions(quizService.getQuestion());
        model.addAttribute("questionForm", questionForm);
        return "QuizPage";
    }

    @PostMapping("/submit")
    public String submit (@ModelAttribute QuestionForm questionForm, Model model){
        if(!submitted){
            int totalCorrect = quizService.getResult(questionForm.getQuestions());

            result.setTotalCorrect(totalCorrect);
            quizService.saveResult(result);
            submitted = true;
        }
        model.addAttribute("result", result);
        return "ResultPage";
    }

    @GetMapping("/score")
    public String score(Model m){
        List<Result> resultList = quizService.getTopScore();
        m.addAttribute("resultList", resultList);
        return "ScorePage";
    }

}
