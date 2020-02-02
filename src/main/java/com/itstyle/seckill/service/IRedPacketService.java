package com.itstyle.seckill.service;

import com.itstyle.seckill.common.entity.Result;

public interface IRedPacketService {


	/**
	 * 秒杀一
	 * @param redPacketId
	 * @return
	 */
	Result startSeckil(long redPacketId,int userId);

    /**
     * 秒杀二
     * @param redPacketId
     * @param userId
     * @return
     */
    Result startTwoSeckil(long redPacketId,int userId);

}
