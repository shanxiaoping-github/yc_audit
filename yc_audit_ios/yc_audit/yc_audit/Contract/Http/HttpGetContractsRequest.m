//
//  HttpGetContractsRequest.m
//  yc_audit
//
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import "HttpGetContractsRequest.h"
#import "HttpContant.h"
#import "JsonFactory.h"
#import "ContractIntroduction.h"
@implementation HttpGetContractsRequest
@synthesize auditStatus = _auditStatus;
@synthesize contracts = _contracts;
-(NSString *)url{
    return BASE_URL;
}
-(NSString *)action{
    if (_auditStatus == 0) {
        return @"/getPartContractList.action";
    }else{
        return @"/getCompleteContractList.action";
    }
}
-(NSArray *)getPramaKeys{
    return @[@"action",@"userName",@"page"];
}
-(void)parseResponse:(NSString *)resultType result:(NSString *)result response:(id)response{
    if ([resultType isEqualToString:NOMAL]) {
        self.contracts = [JsonFactory creatJsonDataArrayByArray:response[@"contract"] class:[ContractIntroduction class]];
    }
}
@end
