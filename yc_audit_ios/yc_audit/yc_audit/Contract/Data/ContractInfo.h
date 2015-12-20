//
//  ContractInfo.h
//  yc_audit
//  合同基本描述
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import <Foundation/Foundation.h>
@interface ContractInfo : NSObject
@property(nonatomic,copy)NSString* contractId;
@property(nonatomic,copy)NSString* date1;
@property(nonatomic,copy)NSString* startDate;
@property(nonatomic,copy)NSString* endDate;
@property(nonatomic,copy)NSString* supplierName;
@property(nonatomic,copy)NSString* txt7;
@end
