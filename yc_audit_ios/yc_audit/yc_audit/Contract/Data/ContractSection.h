//
//  ContractSelection.h
//  yc_audit
//  合同菜单选项
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//
/*section*/
#define contract_section_info @"合同详情"
#define contract_section_terms @"合同条款"
#define contract_section_product @"产品"
#define contract_section_audit_record @"审核纪录"
#define contract_section_audit_operation @"审核操作"
/*cell*/
#define contract_cell_info @"InfoCell"
#define contract_cell_terms @"TermsCell"
#define contract_cell_product @"ProductCell"
#define contract_cell_audit_record @"AuditRecordCell"
#define contract_cell_audit_operation @"AuditOperationCell"
#import <Foundation/Foundation.h>
@interface ContractSection : NSObject
/*selection描述*/
@property(nonatomic,copy)NSString* sectionDesc;
/*cell文件*/
@property(nonatomic,copy)NSString* cellNibName;
/*
 *获取合同审核sections
 */
+(NSArray*)getContracSections;
@end
