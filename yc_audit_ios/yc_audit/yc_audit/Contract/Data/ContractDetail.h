//
//  ContractDetail.h
//  yc_audit
//  合同详情
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//
#define term_name @"termname"
#import <Foundation/Foundation.h>
#import "ContractInfo.h"
@interface ContractDetail : NSObject
/*合同概要*/
@property(nonatomic,strong)NSArray<ContractInfo*>* contractInfo;
/*合同产品数据*/
@property(nonatomic,strong)NSArray* products;
/*合同名称*/
@property(nonatomic,copy)NSDictionary* bdPayterm;
/*合同组织名称*/
@property(nonatomic,copy)NSString* orgName;
@property(nonatomic,copy)NSString* crateContractOrg;
/*合同审核记录*/
@property(nonatomic,copy)NSArray* checkInfos;
@end
