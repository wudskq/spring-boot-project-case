package cn.com.wudskq.service;

 
import cn.com.wudskq.model.dto.TSysRes;

import java.util.List;
 

public interface TSysResService {
 
    List<TSysRes> findResByUserId(String userId);
 
}

