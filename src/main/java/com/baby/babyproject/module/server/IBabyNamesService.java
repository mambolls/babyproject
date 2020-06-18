package com.baby.babyproject.module.server;

import com.baby.babyproject.module.dao.entity.BabyNames;
import com.baby.babyproject.module.request.BabyNameReq;
import com.baby.babyproject.module.request.BabyNamesDelReq;
import com.baby.babyproject.module.request.BabyNamesListReq;
import com.baby.babyproject.module.request.BabyNamesSaveReq;
import com.baby.babyproject.module.response.BabyNamesRsp;
import com.baby.babyproject.util.Result;
import com.github.pagehelper.PageInfo;

public interface IBabyNamesService {

    Result<BabyNames> insertBabyName(BabyNamesSaveReq req);

    Result<PageInfo<BabyNamesRsp>> listBabyNames(BabyNamesListReq req);

    Result<String> deleteBabyNames(BabyNamesDelReq req);

    Result<BabyNamesRsp> getOne(BabyNameReq req);
}
