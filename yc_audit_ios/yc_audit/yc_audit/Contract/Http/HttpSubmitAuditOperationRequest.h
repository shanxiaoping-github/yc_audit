//
//  HttpSubmitAuditOperationRequest.h
//  yc_audit
//  提交审核操作
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//
#define audit_action @"saveContractCheckResult"
#import "HttpRequest.h"
@interface HttpSubmitAuditOperationRequest : HttpRequest
/*
 *审核提交状态
 * 0 审核成功,进入下一级审核
 * -1 审核结束,合同审核状态保存失败
 * 1 审核结束,合同审核状态保存成功
 */
@property(nonatomic,strong)NSNumber* flag;
@end
