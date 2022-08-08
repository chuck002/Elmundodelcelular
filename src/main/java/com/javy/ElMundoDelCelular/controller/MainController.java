package com.javy.ElMundoDelCelular.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.hibernate.sql.results.LoadingLogger_.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javy.ElMundoDelCelular.repository.CelularRepository;
import com.javy.ElMundoDelCelular.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MainController {
    
    @Autowired
    CelularRepository celularRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

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

    @RequestMapping(value="/home/search/muestra/{id}", method = RequestMethod.GET)
    public String getMuestraCelular(Model model, @PathVariable Integer id){

        model.addAttribute("celular", celularRepository.findByCelular(id));

        return "/muestra";
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

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "/login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String getLoginTest(Model model, @Param ("user") String user, @Param ("pass") String pass) {
        List<Object[]> datosUser = usuarioRepository.findByUsuario(user, pass);
        logger.info("datos ---> {}", datosUser);
        //logger.info("dato --> {}", datosUser.get(0)[0]);
        String result = "/login";
        if(datosUser == null || datosUser.isEmpty()){
            result = "/login";
        }else if(datosUser.size() == 1 && Integer.parseInt(datosUser.get(0)[4].toString()) == 1){
            result = "/admin";
        }else{
            result = "/cliente";
        }
        return result;
    }

    @RequestMapping(value="/admin", method = RequestMethod.POST)
    public String getAdmin() {
        return "/admin";
    }

    @RequestMapping(value="/cliente", method = RequestMethod.POST)
    public String getCliente() {
        return "/cliente";
    }
}
