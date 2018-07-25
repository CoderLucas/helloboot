package com.helloboot.service;

import com.helloboot.domain.TbUser;
import com.helloboot.dao.TbUserMapper;
import com.helloboot.service.TbUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author lujianhao
 * @since 2018-07-24
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

}
