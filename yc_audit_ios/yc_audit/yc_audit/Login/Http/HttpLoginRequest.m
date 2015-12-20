//
//  HttpLoginRequest.m
//  yc_audit
//
//  Created by 单小萍 on 15/12/19.
//  Copyright © 2015年 单小萍. All rights reserved.
//
#import "HttpContant.h"
#import "HttpLoginRequest.h"
#import "JsonFactory.h"
@implementation HttpLoginRequest
@synthesize status = _status;
@synthesize userInfo = _userInfo;
/*
 返回地址
 */
-(NSString *)url{
    return BASE_URL;
}
-(NSString *)action{
    return @"/loginTest.action";
}

/*
 返回参数键值
 */
-(NSArray *)getPramaKeys{
    return @[@"action",@"userName",@"passWord"];
}
/*
 数据解析
 */
-(void)parseResponse:(NSString *)resultType result:(NSString *)result response:(id)response{
    if ([resultType isEqualToString:NOMAL]) {
        NSNumber* statusNumber = response[@"status"];
        self.status = [statusNumber stringValue];
        if ([_status isEqualToString:@"0"]) {
            self.userInfo = [JsonFactory creatJsonDataItemByDic:response[@"userInfo"]class:[UserInfo class]];
        }
    }
}
@end
