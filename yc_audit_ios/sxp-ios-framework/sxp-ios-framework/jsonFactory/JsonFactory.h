//
//  JsonFactory.h
//  apos-enterprise-ios
//
//  Created by shanxiaoping on 14-12-8.
//  Copyright (c) 2014年 cpz. All rights reserved.
//

#import <Foundation/Foundation.h>
@interface JsonFactory : NSObject
//根据字符串创建jsonData数组
+(NSArray*)creatJsonDataArrayByStr:(NSString*)jsonStr class:(Class)className;
//根据字符串创建jsonData字典数组
+(NSArray*)creatJsonDataArrayByStr:(NSString*)jsonStr;
//根据数组创建jsonData数组
+(NSArray*)creatJsonDataArrayByArray:(NSArray*)jsonArray class:(Class)className;
//根据字符串创建jsonData字典
+(NSDictionary*)creatJsonDataItem:(NSString*)jsonStr;
//根据字符串创建jsonData
+(id)creatJsonDataItemByStr:(NSString*)jsonStr class:(Class)className;
//根据字典创建jsonData
+(id)creatJsonDataItemByDic:(NSDictionary*)jsonItem class:(Class)className;
@end
