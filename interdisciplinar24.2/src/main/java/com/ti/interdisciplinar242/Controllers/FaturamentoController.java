package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.Interfaces.FaturamentoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faturamento")
public class FaturamentoController {

    @Autowired
    FaturamentoInterface faturamentoInterface;
}


