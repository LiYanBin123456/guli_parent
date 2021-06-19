package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Contact;
import com.atguigu.eduservice.mapper.ContactMapper;
import com.atguigu.eduservice.service.ContactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 黎燕斌
 * @since 2021-06-11
 */
@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements ContactService {

}
