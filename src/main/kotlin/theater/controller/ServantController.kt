package theater.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import theater.service.EmployeeService

@RestController
@RequestMapping("/servants")
class ServantController(private val service: EmployeeService) {

}