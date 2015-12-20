//
//  HttpLoginRequest.h
//  yc_audit
//  登录请求
//  Created by 单小萍 on 15/12/19.
//  Copyright © 2015年 单小萍. All rights reserved.
//
#define login_action @"loginTest"
#import "HttpRequest.h"
#import "UserInfo.h"
@interface HttpLoginRequest : HttpRequest
/*返回状态*/
@property(nonatomic,copy)NSString* status;
/*登录对象*/
@property(nonatomic,strong)UserInfo* userInfo;
@end
