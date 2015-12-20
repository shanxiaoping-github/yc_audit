//
//  ContractProducts.h
//  yc_audit
//  合同产品
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import <Foundation/Foundation.h>
@interface ContractProducts : NSObject
/*合同编号*/
@property(nonatomic,copy)NSString* contractId;
/*产品id*/
@property(nonatomic,copy)NSString* productid;
/*产品姓名*/
@property(nonatomic,copy)NSString* productname;
/*数量*/
@property(nonatomic,copy)NSString* quantity;
/*总*/
@property(nonatomic,copy)NSString* total;
/*单位*/
@property(nonatomic,copy)NSString* unit;
/*单价*/
@property(nonatomic,copy)NSString* unitprice;
@end
