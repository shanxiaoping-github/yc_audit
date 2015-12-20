//
//  HttpGetContractDetailRequest.h
//  yc_audit
//  获取合同详情
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//
#define detail_action @"getContractResultInfo"
#import "HttpRequest.h"
#import "ContractDetail.h"
@interface HttpGetContractDetailRequest : HttpRequest
@property(nonatomic,strong)ContractDetail* contractDetail;
@end
