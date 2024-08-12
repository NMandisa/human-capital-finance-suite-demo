package za.co.mkhungo.payroll.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@CrossOrigin(allowedHeaders = {"*"}, origins = "*")
@RestController
@RequestMapping("api/payroll")
public class PayrollRootController {
}
