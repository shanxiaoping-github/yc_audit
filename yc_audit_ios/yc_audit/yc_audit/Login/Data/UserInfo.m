//
//  UserInfo.m
//  yc_audit
//  用户登录信息
//  Created by 单小萍 on 15/12/19.
//  Copyright © 2015年 单小萍. All rights reserved.
//
#import "UserInfo.h"
@implementation UserInfo
@synthesize ids = _ids;
@synthesize userName = _userName;
@synthesize realName = _realName;
+(NSDictionary *)replacedKeyFromPropertyName{
    return @{@"ids":@"id"};
}
@end
