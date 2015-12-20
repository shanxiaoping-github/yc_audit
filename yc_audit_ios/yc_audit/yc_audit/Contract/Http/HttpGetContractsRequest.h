//
//  HttpGetContractsRequest.h
//  yc_audit
//  获取合同列表
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//
#define wait_contract_action @"getPartContractList"
#define already_contract_action @"getCompleteContractList"
#import "HttpRequest.h"
@interface HttpGetContractsRequest : HttpRequest
/*
 *审核状态 0待审核 1已审核
 */
@property(nonatomic,assign)NSInteger auditStatus;
/*合同数组*/
@property(nonatomic,strong)NSArray* contracts;
@end
