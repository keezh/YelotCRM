package com.yelot.crm.service;

import com.yelot.crm.entity.RepareOrder;
import com.yelot.crm.entity.RepareOrderItem;
import com.yelot.crm.entity.RepareOrderItemImage;
import com.yelot.crm.mapper.RepareOrderItemImgMapper;
import com.yelot.crm.mapper.RepareOrderItemMapper;
import com.yelot.crm.mapper.RepareOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kee on 17/6/5.
 */
@Service
@Transactional
public class RepareOrderService {

    @Autowired
    private RepareOrderMapper repareOrderMapper;

    @Autowired
    private RepareOrderItemMapper repareOrderItemMapper;

    @Autowired
    private RepareOrderItemImgMapper repareOrderItemImgMapper;

    RepareOrder find(Long id){
        return repareOrderMapper.find(id);
    }

    /**
     * 保存订单业务逻辑
     * @param repareOrder
     * @param repareOrderItem
     * @param repareOrderItemImage
     */
    void save(RepareOrder repareOrder, RepareOrderItem repareOrderItem, RepareOrderItemImage repareOrderItemImage){
        //step 1:首先保存订单表
        repareOrderMapper.save(repareOrder);
        RepareOrder repareOrder1 = repareOrderMapper.findByOrderNo(repareOrder.getOrder_no());

        //step 2:保存item表
        repareOrderItem.setRepare_order_id(repareOrder1.getId());
        repareOrderItemMapper.save(repareOrderItem);

        //step 3:保存图片
        List<RepareOrderItem> repareOrderItemList = repareOrderItemMapper.findByOrderId(repareOrder1.getId());
        if(repareOrderItemList != null && repareOrderItemList.size()>0){
            //当前不用修改，一个订单中只有一个item,取第一个也是唯一的一个即可。后期如果扩展成多个，需要修改.
            repareOrderItemImage.setRepare_order_item_id(repareOrderItemList.get(0).getId());
        }
        repareOrderItemImgMapper.save(repareOrderItemImage);
    }


}
