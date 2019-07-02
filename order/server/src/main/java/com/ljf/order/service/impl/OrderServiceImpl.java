package com.ljf.order.service.impl;

import com.ljf.order.dataobject.OrderDetail;
import com.ljf.order.dataobject.OrderMaster;
import com.ljf.order.dto.OrderDTO;
import com.ljf.order.enums.OrderStatusEnum;
import com.ljf.order.enums.PayStatusEnum;
import com.ljf.order.enums.ResultEnum;
import com.ljf.order.exception.OrderException;
import com.ljf.order.repository.OrderDetailRepository;
import com.ljf.order.repository.OrderMasterRepository;
import com.ljf.order.service.OrderService;
import com.ljf.order.utils.KeyUtil;
import com.ljf.product.client.ProductClient;
import com.ljf.product.common.DecreaseStockInput;
import com.ljf.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by mr.lin on 2019/6/20
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        //总价
        List<String> productIdList = orderDTO.getOrderDetailList()
                .stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoOutputList = productClient.listForOrder(productIdList);


        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
                if (orderDetail.getProductId().equals(productInfoOutput.getProductId())) {
                    orderAmount = productInfoOutput.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);

                    BeanUtils.copyProperties(productInfoOutput, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());

                    orderDetailRepository.save(orderDetail);
                }
            }

        }

        //库存
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList()
                .stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        productClient.decreaseStock(decreaseStockInputList);


        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {

        Optional<OrderMaster> optional = orderMasterRepository.findById(orderId);
        if (!optional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderMaster orderMaster = optional.get();

        if (OrderStatusEnum.NEW.getCode() != orderMaster.getOrderStatus()) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}
