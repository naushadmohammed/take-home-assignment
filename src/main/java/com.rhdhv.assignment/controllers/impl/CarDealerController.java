package com.rhdhv.assignment.controllers.impl;

import com.rhdhv.assignment.controllers.ICarDealerController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Application Rest Controller Implementation.
 */
@RestController
@RequestMapping(value = "cars")
@Validated
@RequiredArgsConstructor
@ResponseBody
public class CarDealerController implements ICarDealerController {


}