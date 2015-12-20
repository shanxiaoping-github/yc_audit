//
//  ContractIntroduction.h
//  yc_audit
//  合同描述对象
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ContractIntroduction : NSObject
/*合同编号*/
@property(nonatomic,copy)NSString* contractId;
/*送审人*/
@property(nonatomic,copy)NSString* operater;
/*供货商名称*/
@property(nonatomic,copy)NSString* supplierName;
/*审核状态*/
@property(nonatomic,copy)NSString* status;
/*审核编号*/
@property(nonatomic,copy)NSString* processId;
/*审核节点*/
@property(nonatomic,copy)NSString* processNode;
@end
