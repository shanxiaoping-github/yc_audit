//
//  HttpSubmitAuditOperationRequest.m
//  yc_audit
//
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import "HttpSubmitAuditOperationRequest.h"
#import "HttpContant.h"
@implementation HttpSubmitAuditOperationRequest
@synthesize flag = _flag;

-(NSString *)url{
    return BASE_URL;
}

-(NSString *)action{
    return @"/saveContractCheckResult.action";
}
-(NSArray *)getPramaKeys{
    return @[@"action",@"contractId",@"userName",@"status",@"opinion",@"processId",@"processNode"];
}
-(void)parseResponse:(NSString *)resultType result:(NSString *)result response:(id)response{
    self.flag = [NSNumber numberWithInt:-2];
    if ([resultType isEqualToString:NOMAL]) {
        self.flag = response[@"flag"];
    }
}
@end
