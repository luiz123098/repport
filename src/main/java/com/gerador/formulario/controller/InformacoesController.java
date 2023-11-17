package com.gerador.formulario.controller;

import com.gerador.formulario.model.dto.InformacoesDTO;
import com.gerador.formulario.model.entity.Informacoes;
import com.gerador.formulario.model.services.InformacoesService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/formulario")
public class InformacoesController {

    @Autowired
    InformacoesService informacoesService;
    @PostMapping(path = "/registro")
    public ResponseEntity registrarUsuarios(@RequestBody InformacoesDTO informacoesDTO){
        try {
            Informacoes info = new Informacoes();
            info.setNome(informacoesDTO.getNome());
            info.setIdade(informacoesDTO.getIdade());
            info.setSexo(informacoesDTO.getSexo());

            informacoesService.registrarInformacoes(info);
            return new ResponseEntity(info, HttpStatus.ACCEPTED);

        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                informacoesService.findAll());

        JasperReport compileReport = JasperCompileManager.compileReport(
                this.getClass().getResourceAsStream("/templates/formulario.jrxml"));

        HashMap<String, Object> map = new HashMap<>();
        JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);

        byte[] data = JasperExportManager.exportReportToPdf(report);

        HttpHeaders headers = new HttpHeaders();
        //headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Content-Disposition");
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=formulario.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }
}
