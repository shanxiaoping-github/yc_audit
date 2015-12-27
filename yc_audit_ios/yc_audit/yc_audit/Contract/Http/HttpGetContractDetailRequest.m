//
//  HttpGetContractDetailRequest.m
//  yc_audit
//
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import "HttpGetContractDetailRequest.h"
#import "HttpContant.h"
#import "JsonFactory.h"
@implementation HttpGetContractDetailRequest
@synthesize contractDetail = _contractDetail;
-(NSString *)url{
    return BASE_URL;
}
-(NSString *)action{
    return @"/getContractResultInfo.action";
}

-(NSArray *)getPramaKeys{
    return @[@"action",@"userName",@"contractId",@"processId",@"processNode"];
}
-(void)parseResponse:(NSString *)resultType result:(NSString *)result response:(id)response{
    if ([resultType isEqualToString:NOMAL]) {
       self.contractDetail = [JsonFactory creatJsonDataItemByDic:response class:[ContractDetail class]];
    }
}

@end
