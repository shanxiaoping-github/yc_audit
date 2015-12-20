//
//  CheckInfo.h
//  yc_audit
//  审核纪录
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//

#import <Foundation/Foundation.h>
@interface CheckInfo : NSObject
/*审核岗位*/
@property(nonatomic,copy)NSString* rolename;
/*审核人姓名*/
@property(nonatomic,copy)NSString* realname;
/*已审核意见*/
@property(nonatomic,copy)NSString* opinion;
@end
