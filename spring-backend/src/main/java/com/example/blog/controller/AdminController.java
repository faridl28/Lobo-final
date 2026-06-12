package com.example.blog.controller;

import com.example.blog.model.Solicitud;
import com.example.blog.service.SolicitudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final SolicitudService solicitudService;

    public AdminController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping("/solicitudes/panel")
    public String panel(Model model) {
        List<Solicitud> solicitudes = solicitudService.todas();
        model.addAttribute("solicitudes", solicitudes);
        model.addAttribute("total", solicitudService.contarTodas());
        model.addAttribute("pendientes", solicitudService.contarPorEstado(Solicitud.Estado.PENDIENTE));
        model.addAttribute("aprobadas", solicitudService.contarPorEstado(Solicitud.Estado.APROBADA));
        model.addAttribute("rechazadas", solicitudService.contarPorEstado(Solicitud.Estado.RECHAZADA));
        return "panel-solicitudes";
    }
}