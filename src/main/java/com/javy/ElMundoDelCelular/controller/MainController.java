package com.javy.ElMundoDelCelular.controller;

import java.sql.Date;
import java.time.ZonedDateTime;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javy.ElMundoDelCelular.repository.EquipoRepository;
import com.javy.ElMundoDelCelular.repository.PedidoRepository;
import com.javy.ElMundoDelCelular.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MainController {
    
    @Autowired
    EquipoRepository equipoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value={"/home","/","index"})
    public String getHome(Model model) {
        List<Object[]> datos = equipoRepository.findAllBrands();

        model.addAttribute("marcas", equipoRepository.findAllMarca());
        model.addAttribute("celulares", datos);
        return "/home";
    }


    @RequestMapping(value = "/home/search/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Object> getCelularBrands(HttpServletRequest request, RedirectAttributes flash, @PathVariable Integer id) {
        Map<Object, Object> responseService = new HashMap<>();

        try {
            responseService.put("celulares", equipoRepository.findByIdBrand(id));
            return new ResponseEntity<>(responseService, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/home/search/{id}/{idOrden}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Object> getCelularBrandsOrden(HttpServletRequest request, RedirectAttributes flash, @PathVariable Integer id, @PathVariable Integer idOrden) {
        Map<Object, Object> responseService = new HashMap<>();

        try {
            if(idOrden == 0){
                responseService.put("celulares", equipoRepository.findByIdBrand(id));
            }else if(idOrden == 1){
                responseService.put("celulares", equipoRepository.findAllPrecioMayor(id));
            }else{
                responseService.put("celulares", equipoRepository.findAllPrecioMenor(id));
            }
           
            return new ResponseEntity<>(responseService, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/home/search/muestra/{id}", method = RequestMethod.GET)
    public String getMuestraCelular(Model model, @PathVariable Integer id){

        model.addAttribute("pedidos", pedidoRepository.findAllPedidoById(id));

        return "/muestra";
    }

    @RequestMapping(value="/home/search/muestra/pedido/{id}", method = RequestMethod.GET)
    public String getMuestraPedido(Model model, @PathVariable Integer id){

        model.addAttribute("pedido", equipoRepository.findByPedido(id));

        return "/pedido";
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
            model.addAttribute("listaUsuarios", usuarioRepository.findByAllUsuario());
            logger.info("usuarios ---> {}", usuarioRepository.findByAllUsuario());

            result = "/admin";
        }else{
            logger.info("cliente entramos ");
            model.addAttribute("marcas", equipoRepository.findAllMarca());
            model.addAttribute("celulares", equipoRepository.findAllBrands());
            result = "/cliente";
        }
        return result;
    }

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String getAdmin(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findByAllUsuario());
        logger.info("usuarios ---> {}", usuarioRepository.findByAllUsuario());

        return "/admin";
    }

    @RequestMapping(value="/cliente", method = RequestMethod.GET)
    public String getCliente(Model model) {

        logger.info(" estamos en clientes ");
        model.addAttribute("marcas", equipoRepository.findAllMarca());
        model.addAttribute("celulares", equipoRepository.findAllBrands());
        return "/cliente";
    }

    @RequestMapping(value="/cliente/pedido/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Object> getEquiposPedido(Model model, @PathVariable int id) {

        Map<Object, Object> responseService = new HashMap<>();
        logger.info(" pedidos --- {}", id);

        try {
            responseService.put("equipo", equipoRepository.findByIdEquipo(id));
           
            return new ResponseEntity<>(responseService, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/home/user/add", method=RequestMethod.POST)
    public String addUser(HttpServletRequest request, RedirectAttributes flash, Model model) {

        model.addAttribute("listaUsuarios", usuarioRepository.findByAllUsuario());
        return "/admin";
    }
    
    @RequestMapping(value="/home/user/delete/{id}", method=RequestMethod.DELETE)
    public String deleteUser(Model model, @PathVariable ("id") Integer id) {
        usuarioRepository.deleteUsuario(id);
        model.addAttribute("listaUsuarios", usuarioRepository.findByAllUsuario());
        return "/admin";
    }


@RequestMapping(value = "/cliente/pedido/{idEquipo}/{precioTotal}/{idPedido}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createReferencesApi(HttpServletRequest request, RedirectAttributes flash, 
            @PathVariable(value = "idEquipo") int idEquipo,
            @PathVariable(value = "precioTotal") Double precioTotal,
            @PathVariable(value = "idPedido") int idPedido) {
                ZonedDateTime ahora = ZonedDateTime.now();

                pedidoRepository.addPedido(ahora, precioTotal);
                pedidoRepository.addPedido_Equipos(idEquipo, idEquipo);
        //logger.info("Referencia Agregada clientId: {}, Nombre: {}, Parentesco: {}, Telefono: {}", clientId, nameRef, perentRef, telfRef);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
