package com.javy.ElMundoDelCelular.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javy.ElMundoDelCelular.entities.Celular;
import com.javy.ElMundoDelCelular.repository.CelularRepository;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MainController {
    
    @Autowired
    CelularRepository celularRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value={"/home","/","index"})
    public String getHome(Model model) {
        List<Object[]> datos = celularRepository.findAllBrands();

        model.addAttribute("marcas", celularRepository.findAllMarca());
        model.addAttribute("celulares", datos);
        return "/home";
    }


    @RequestMapping(value = "/home/search/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Object> getCelularBrands(HttpServletRequest request, RedirectAttributes flash, @PathVariable Integer id) {
        Map<Object, Object> responseService = new HashMap<>();

        try {
            responseService.put("celulares", celularRepository.findByIdBrand(id));
            return new ResponseEntity<>(responseService, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value="/contactanos")
    public String getContactanos() {
        return "/contactanos";
    }
    @GetMapping(value="/nosotros")
    public String getNosotros() {
        return "/quienessomos";
    }
    @GetMapping(value="/servicios")
    public String getServicios() {
        return "/servicios";
    }
    
}
