package org.example.springbootpaymentgatewayrazorpay.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.example.springbootpaymentgatewayrazorpay.service.RazorpayService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("http://127.0.0.1:5500")
public class PaymentController {

    @Autowired
    private RazorpayService razorpayService;

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestParam int amount, @RequestParam String currency) {
        try {
            RazorpayClient razorpay = new RazorpayClient("rzp_test_38I5IEufjhiOFj", "XCrXx93If1q1cuILOpXcggmb");

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount); // amount in paise
            orderRequest.put("currency", currency);
            orderRequest.put("receipt", "order_rcptid_11");
            orderRequest.put("payment_capture", true);

            Order order = razorpay.orders.create(orderRequest);
            return ResponseEntity.ok(order.toString());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
