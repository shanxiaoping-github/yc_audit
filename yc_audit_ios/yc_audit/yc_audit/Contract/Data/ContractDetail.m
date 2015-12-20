//
//  ContractDetail.m
//  yc_audit
//
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//
#import "ContractDetail.h"
#import "MJExtension.h"
#import "ContractProducts.h"
#import "CheckInfo.h"
@implementation ContractDetail
@synthesize contractInfo = _contractInfo;
@synthesize products = _products;
@synthesize bdPayterm = _bdPayterm;
@synthesize orgName = _orgName;
@synthesize crateContractOrg = _crateContractOrg;
@synthesize checkInfos = _checkInfos;

+(NSDictionary *)replacedKeyFromPropertyName{
    return @{@"products":@"contractProducts",@"checkInfos":@"hasCheck"};
}
+(NSDictionary *)objectClassInArray{
    
    return @{@"contractInfo":[ContractInfo class],@"products":[ContractProducts class],@"checkInfos":[CheckInfo class]};
}
@end
