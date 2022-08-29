package com.example.orderservice.Controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.orderservice.Data.Order.OrderInfo;
import com.example.orderservice.Data.User.User;
import com.example.orderservice.Exception.Base.ViewException;
import com.example.orderservice.Result.CodeMsg;
import com.example.orderservice.Service.OrderService;
import com.example.orderservice.Service.UserService;


@Controller
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;
     
    @PostMapping
    @ResponseBody
    public CodeMsg setOrder(@RequestBody OrderInfo info, HttpServletRequest request) {
        logger.info("begin set order, goodId is {}", info.getGoodsId());
        
        User user = userService.getUserByToken(request);
        if (user == null) {
            throw new ViewException(CodeMsg.REQUEST_USER_NOT_FOUND);
        }
        info.setUserId(user.getId());
        
        return orderService.setOrder(user, info);           

    }
}
