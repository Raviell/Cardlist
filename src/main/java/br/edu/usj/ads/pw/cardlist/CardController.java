package br.edu.usj.ads.pw.cardlist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;







@Controller
public class CardController {

    @Autowired
    CardRepository cardRepository;   
    
    @GetMapping(value="cadastrados")
    public ModelAndView getCadastrados() {
        List<Card> lista = new ArrayList<>();        
        lista = cardRepository.findAll();       
        ModelAndView modelAndView = new ModelAndView("cadastrados");        
        modelAndView.addObject("lista", lista);   
        return modelAndView;

        
    }   
   


    @GetMapping(value="/detalhes/{id}")
    public ModelAndView getInformacoes(@PathVariable Long id) {        
        
        Card card = new Card();
        card = cardRepository.findById(id).get();        
        ModelAndView modelAndView = new ModelAndView("informacoes"); 
        modelAndView.addObject("card", card);     
        return modelAndView;
    }   

    
    
    
    @GetMapping(value="/cadastro")
    public ModelAndView getCadastro() {

        Card card = new Card();
        ModelAndView modelAndView = new ModelAndView("cadastro");        
        modelAndView.addObject("card", card);
        return modelAndView;
    }
    

    @PostMapping(value="/adicionar")
    public ModelAndView postAdicionar(Card card) {
        
        cardRepository.save(card);      
        ModelAndView modelAndView = new ModelAndView("informacoes");        
        modelAndView.addObject("card", card);        
        return modelAndView;
    }

    
    @GetMapping(value="/editar/{id}")
    public ModelAndView getEditar(@PathVariable Long id) {
        
        Card card = new Card();
        card = cardRepository.findById(id).get();       
        ModelAndView modelAndView = new ModelAndView("cadastro");       
        modelAndView.addObject("card", card);       
        return modelAndView;
    }
    
    
    @GetMapping(value="/deletar/{id}")
    public String getDeletar(@PathVariable Long id) {
        
        cardRepository.deleteById(id);
        return "redirect:/cadastrados";

        
    }
    
    
}
