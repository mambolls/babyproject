package com.baby.babyproject.module.server;

import com.baby.babyproject.module.request.BabyNamesDelReq;
import com.baby.babyproject.module.request.BabyNamesListReq;
import com.baby.babyproject.module.request.BabyNamesSaveReq;
import com.baby.babyproject.module.response.BabyNamesRsp;
import com.baby.babyproject.util.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBabyNamesService {

    public Result<String> insertBabyName(BabyNamesSaveReq req);

    public Result<PageInfo<BabyNamesRsp>> listBabyNames(BabyNamesListReq req);

    public Result<String> deleteBabyNames(BabyNamesDelReq req);
}
