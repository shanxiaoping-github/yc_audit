//
//  ContractSelection.m
//  yc_audit
//
//  Created by 单小萍 on 15/12/20.
//  Copyright © 2015年 单小萍. All rights reserved.
//
#import "ContractSection.h"
@implementation ContractSection
@synthesize sectionDesc = _sectionDesc;
@synthesize cellNibName = _cellNibName;

+(NSArray *)getContracSections{
    NSArray* sectionDescs = @[contract_section_info,contract_section_terms,contract_section_product,contract_section_audit_record,contract_section_audit_operation];
    NSArray* cells = @[contract_cell_info,contract_cell_terms,contract_cell_product,contract_cell_audit_record,contract_cell_audit_operation];
    NSMutableArray* contractSections = [NSMutableArray new];
    for (int i = 0; i < sectionDescs.count;i++) {
        ContractSection* contractSection = [ContractSection new];
        contractSection.sectionDesc = sectionDescs[i];
        contractSection.cellNibName = cells[i];
        [contractSections addObject:contractSection];
    }
    return contractSections;
}

@end
