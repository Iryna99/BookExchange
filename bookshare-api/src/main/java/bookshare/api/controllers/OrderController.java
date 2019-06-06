package bookshare.api.controllers;

import bookshare.api.entities.OrderEntity;
import bookshare.api.repositories.OrderRepository;
import bookshare.api.repositories.impl.OrderRepositoryImpl;
import bookshare.api.utils.UserTmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping(value = "/api/order")
    public ResponseEntity<List<OrderEntity>> getAllUsers() throws SQLException {
        List<OrderEntity> orderEntities = orderRepository.selectAll();
        return new ResponseEntity<>(orderEntities, HttpStatus.OK);
    }

    @GetMapping(value = "/api/order/{user_id}")
    public ResponseEntity<OrderEntity> getBook(@PathVariable Integer user_id) throws SQLException {
        OrderEntity order = orderRepository.findById(user_id);
        return new ResponseEntity<>(order, HttpStatus.OK);

    }

    @PostMapping("/api/order/add")
    public ResponseEntity<OrderEntity> register(@RequestBody OrderEntity regRequest) throws Exception {
        System.out.println(regRequest);
        var reg = orderRepository.insert(new OrderEntity(new UserTmp().getUserId(),
                regRequest.getAnnounceId(),
                regRequest.getComment(),
                true));
        return new ResponseEntity<>(reg, HttpStatus.OK);
    }

}